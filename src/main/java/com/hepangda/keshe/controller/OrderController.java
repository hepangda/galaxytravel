package com.hepangda.keshe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.model.Order;
import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.AirlineService;
import com.hepangda.keshe.service.AirplaneService;
import com.hepangda.keshe.service.FlightService;
import com.hepangda.keshe.service.OrderService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class OrderController extends GenericController {

  @Autowired
  OrderService srv;

  @Autowired
  FlightService fsrv;

  @Autowired
  AirlineService asrv;

  @Autowired
  AirplaneService psrv;

  @GetMapping("/user/order/create/{clazz}/{id}")
  public String pathCreate(@PathVariable("id") long flightId,
      @PathVariable("clazz") long clazz, Model model) {
    Flight flight = fsrv.getById(flightId);
    model.addAttribute("active", "order");
    model.addAttribute("biz_clazz", clazz);
    model.addAttribute("biz_flight_msg", flight);
    model.addAttribute("biz_seat", fsrv.getSeat(clazz, flightId));
    return "order_creat";
  }

  @GetMapping("/admin/order/list")
  public String pathAdminList(@RequestParam(required = false) Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    model.addAttribute("active", "order");
    model.addAttribute("biz_user_map", srv.getUserMap());
    model.addAttribute("biz_flight_map", srv.getFlightMap());
    model.addAttribute("page_max", srv.getPageMax());
    return "order_list_admin";
  }

  @GetMapping("/user/order/list")
  public String pathUserList(@RequestParam(required = false) Integer page, HttpSession session,
      Model model) {
    User user = (User) session.getAttribute(Constants.SESSION_USER);
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.showUser(user.getId(), ipage));
    model.addAttribute("active", "order");
    model.addAttribute("biz_user_map", srv.getUserMap());
    model.addAttribute("biz_flight_map", srv.getFlightMap());
    model.addAttribute("page_max", srv.getPageMax(user.getId()));

    return "order_list_user";
  }

  @PostMapping("/api/order/create")
  public String doCreate(@RequestParam Map<String, Object> orderMap, Model model) {
    Order order = getBeanFromBody(Order.class, orderMap);
    model.addAttribute("active", "order");

    try {
      JSONArray array = JSONArray.parseArray((String) orderMap.get("tickets"));
      String[] ticket = array.getString(0).split(",");
      Integer i = Integer.valueOf(ticket[0]);
      Integer j = Integer.valueOf(ticket[1]);
      order.setRow(i);
      order.setCol(j);
      System.err.println(JSON.toJSONString(order));
      return resp(model, () -> srv.add(order), x -> "gotoorder",
          x -> pathCreate(order.getFlightId(), order.getClazz(), model)
      );
    } catch (Exception ex) {
    }

    return pathCreate(order.getFlightId(), order.getClazz(), model);
  }

  @PostMapping("/api/order/delete")
  public String doDelete(@RequestParam long id, Model model) {
    return resp(model, () -> srv.deleteById(id), () -> pathAdminList(1, model));
  }
}
