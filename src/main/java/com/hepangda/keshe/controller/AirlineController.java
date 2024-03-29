package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.service.AirlineService;
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
public class AirlineController extends GenericController {

  @Autowired
  AirlineService srv;

  @GetMapping("/admin/airline/create")
  public String pathCreate(Model model) {
    model.addAttribute("active", "airline");
    model.addAttribute("biz_airport_map", srv.getPortMap());
    return "airline_creat";
  }

  @GetMapping("/admin/airline/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    model.addAttribute("active", "airline");
    model.addAttribute("biz_airport_map", srv.getPortMap());
    return "airline_mod";
  }

  @GetMapping("/admin/airline/list")
  public String pathList(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) String keyword, Model model) {
    int ipage = dealPage(page);
    model.addAttribute("active", "airline");
    model.addAttribute("page_max", srv.getPageMax());
    model.addAttribute("biz_airport_map", srv.getPortMap());
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, keyword));
    if (keyword != null && !keyword.isEmpty()) {
      model.addAttribute("not_query", "yes");
    }
    return "airline_list";
  }

  @PostMapping("/api/airline/create")
  public String doCreate(@RequestParam Map<String, Object> airlineMap, Model model) {
    Airline airline = getBeanFromBody(Airline.class, airlineMap);
    return resp(model, () -> srv.add(airline), i -> pathList(1, null, model), i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "创建失败");
      model.addAttribute("active", "airline");
      model.addAttribute("biz_airport_map", srv.getPortMap());
      return "airline_creat";
    });
  }

  @PostMapping("/api/airline/delete")
  public String doDelete(@RequestParam long id, Model model) {
    model.addAttribute("active", "airline");
    return resp(model, () -> srv.deleteById(id), () -> pathList(1, null, model));
  }

  @PostMapping("/api/airline/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> airlineMap,
      Model model) {
    Airline airline = getBeanFromBody(Airline.class, airlineMap);
    model.addAttribute("active", "airline");
    return resp(model, () -> srv.update(airline), i -> pathList(1, null, model), i -> {
      model.addAttribute("active", "airline");
      model.addAttribute(Constants.GBF_BIZMSG, "修改失败");
      model.addAttribute("biz_airport_map", srv.getPortMap());
      return "airline_mod";
    });
  }

}
