package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.service.AirportService;
import com.hepangda.keshe.service.FlightService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class FlightController extends GenericController {

  @Autowired
  FlightService srv;

  @GetMapping("/admin/flight/create")
  public String pathCreate() {
    return "flight_creat";
  }

  @GetMapping("/admin/flight/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "flight_mod";
  }

  @GetMapping("/admin/flight/list")
  public String pathAdminList(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "flight_list_admin";
  }

  @GetMapping("/user/flight/list")
  public String pathUserList(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "flight_list_admin";
  }

  @PostMapping("/api/flight/create")
  public String doCreate(@RequestBody Flight flight, Model model) {
    return resp(model, () -> srv.add(flight), "flight_list", "flight_creat");
  }

  @PostMapping("/api/flight/delete/{id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), "flight_list");
  }

  @PostMapping("/api/flight/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestBody Flight flight, Model model) {
    return resp(model, () -> srv.update(flight), "flight_list", "flight_mod");
  }
}
