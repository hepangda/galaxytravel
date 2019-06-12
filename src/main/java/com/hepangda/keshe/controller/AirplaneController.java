package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.service.AirplaneService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class AirplaneController extends GenericController {

  @Autowired
  AirplaneService srv;

  @GetMapping("/admin/airplane/create")
  public String pathCreate() {
    return "airplane_creat";
  }

  @GetMapping("/admin/airplane/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "airplane_mod";
  }

  @GetMapping("/admin/airplane/list")
  public String pathList(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "airplane_list";
  }

  @PostMapping("/api/airplane/create")
  public String doCreate(@RequestParam Map<String, Object> airplaneMap, Model model) {
    Airplane airplane = getBeanFromBody(Airplane.class, airplaneMap);
    return resp(model, () -> srv.add(airplane), "airplane_list", "airplane_creat");
  }

  @PostMapping("/api/airplane/delete/{id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), "airplane_list");
  }

  @PostMapping("/api/airplane/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> airplaneMap, Model model) {
    Airplane airplane = getBeanFromBody(Airplane.class, airplaneMap);
    return resp(model, () -> srv.update(airplane), "airplane_list", "airplane_mod");
  }
}
