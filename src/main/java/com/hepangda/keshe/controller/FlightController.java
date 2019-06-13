package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.service.FlightService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import java.util.Map;
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
public class FlightController extends GenericController {

  @Autowired
  FlightService srv;

  @GetMapping("/admin/flight/create")
  public String pathCreate(Model model) {
    model.addAttribute("active", "flight");
    model.addAttribute("biz_airplane_map", srv.getPlaneMap());
    model.addAttribute("biz_airline_map", srv.getLineMap());
    return "flight_creat";
  }

  @GetMapping("/admin/flight/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute("active", "flight");
    model.addAttribute("biz_airplane_map", srv.getPlaneMap());
    model.addAttribute("biz_airline_map", srv.getLineMap());
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "flight_mod";
  }

  @GetMapping("/admin/flight/list")
  public String pathAdminList(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) String from,
      @RequestParam(required = false) String to,
      @RequestParam(required = false) String time,
      Model model) {
    int ipage = dealPage(page);
    model.addAttribute("active", "flight");
    model.addAttribute("biz_airplane_map", srv.getPlaneMap());
    model.addAttribute("biz_airline_map", srv.getLineMap());
    model.addAttribute("page_max", srv.getPageMax());
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, from, to, time));
    if (srv.AllNull(from, to, time)) {
      model.addAttribute("not_query", "yes");
    }

    return "flight_list_admin";
  }


  @GetMapping("/user/flight/list")
  public String pathUserList(@RequestParam Integer page,
      @RequestParam(required = false) String from,
      @RequestParam(required = false) String to,
      @RequestParam(required = false) String time,
      Model model) {
//    int ipage = dealPage(page);
//    model.addAttribute("biz_airplane_map", srv.getPlaneMap());
//    model.addAttribute("biz_airline_map", srv.getLineMap());
//    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, from, to, time));
//    if ((from != null && to != null && time != null) &&
//        (!from.isEmpty() && !to.isEmpty() && !time.isEmpty())) {
//      model.addAttribute("not_query", "yes");
//    }
//
//    return "flight_list_admin";
    return "";
  }

  @PostMapping("/api/flight/create")
  public String doCreate(@RequestParam Map<String, Object> flightMap, Model model) {
    Flight flight = getBeanFromBody(Flight.class, flightMap);
    String scheTime = (String) flightMap.get("scheTimeX");
    if (scheTime != null) {
      scheTime = scheTime.replace('T', ' ');
      flight.setScheTime(scheTime);
    }

    return resp(model, () -> srv.add(flight), i -> pathAdminList(1, null, null, null, model), i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "创建失败");
      model.addAttribute("active", "flight");
      return pathCreate(model);
    });
  }

  @PostMapping("/api/flight/delete/{id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), () -> pathAdminList(1, null, null, null, model));
  }

  @PostMapping("/api/flight/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> flightMap,
      Model model) {
    Flight flight = getBeanFromBody(Flight.class, flightMap);
    return resp(model, () -> srv.update(flight), i -> pathAdminList(1, null, null, null, model),
        i -> {
          model.addAttribute("active", "flight");
          model.addAttribute(Constants.GBF_BIZMSG, "修改失败");
          return "flight_mod";
        });
  }
}
