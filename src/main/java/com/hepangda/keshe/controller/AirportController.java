package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.service.AirportService;
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
public class AirportController extends GenericController {

  @Autowired
  AirportService srv;

  @GetMapping("/admin/airport/create")
  public String pathCreate() {
    return "airport_creat";
  }

  @GetMapping("/admin/airport/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "airport_mod";
  }

  @GetMapping("/admin/airport/list")
  public String pathList(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "airport_list";
  }

  @PostMapping("/api/airport/create")
  public String doCreate(@RequestParam Map<String, Object> airportMap, Model model) {
    Airport airport = getBeanFromBody(Airport.class, airportMap);
    return resp(model, () -> srv.add(airport), "airport_list", "airport_creat");
  }

  @PostMapping("/api/airport/delete/{id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), "airport_list");
  }

  @PostMapping("/api/airport/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> airportMap,
      Model model) {
    Airport airport = getBeanFromBody(Airport.class, airportMap);
    return resp(model, () -> srv.update(airport), "airport_list", "airport_mod");
  }
}
