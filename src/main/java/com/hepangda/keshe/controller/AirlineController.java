package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.service.AirlineService;
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
public class AirlineController extends GenericController {

  @Autowired
  AirlineService srv;

  @GetMapping("/admin/airline/create")
  public String pathCreate() {
    return "airline_creat";
  }

  @GetMapping("/admin/airline/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "airline_mod";
  }

  @GetMapping("/admin/airline/list")
  public String pathList(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "airline_list";
  }

  @PostMapping("/api/airline/create")
  public String doCreate(@RequestBody Airline airline, Model model) {
    return resp(model, () -> srv.add(airline), "airline_list", "airline_creat");
  }

  @PostMapping("/api/airline/delete/{id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), "airline_list");
  }

  @PostMapping("/api/airline/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestBody Airline airline, Model model) {
    return resp(model, () -> srv.update(airline), "airline_list", "airline_mod");
  }

}
