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
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class AirplaneController extends GenericController {

  @Autowired
  AirplaneService srv;

  @GetMapping("/admin/airplane/create")
  public String pathCreate(Model model) {
    model.addAttribute("active", "airplane");
    return "airplane_creat";
  }

  @GetMapping("/admin/airplane/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    model.addAttribute("active", "airplane");
    return "airplane_mod";
  }

  @GetMapping("/admin/airplane/list")
  public String pathList(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) String keyword, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, keyword));
    model.addAttribute("page_max", srv.getPageMax());
    model.addAttribute("active", "airplane");

    if (keyword != null && !keyword.isEmpty()) {
      model.addAttribute("not_query", "yes");
    }
    return "airplane_list";
  }

  @PostMapping("/api/airplane/create")
  public String doCreate(@RequestParam Map<String, Object> airplaneMap, Model model) {
    Airplane airplane = getBeanFromBody(Airplane.class, airplaneMap);
    return resp(model, () -> srv.add(airplane), i -> pathList(1, null, model), i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "创建失败");
      model.addAttribute("active", "airplane");
      return "airplane_creat";
    });
  }

  @PostMapping("/api/airplane/delete")
  public String doDelete(@RequestParam long id, Model model) {
    model.addAttribute("active", "airplane");
    return resp(model, () -> srv.deleteById(id), () -> pathList(1, null, model));
  }

  @PostMapping("/api/airplane/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> airplaneMap,
      Model model) {
    Airplane airplane = getBeanFromBody(Airplane.class, airplaneMap);
    model.addAttribute("active", "airplane");
    return resp(model, () -> srv.update(airplane), i -> pathList(1, null, model), i -> {
      model.addAttribute("active", "airplane");
      model.addAttribute(Constants.GBF_BIZMSG, "修改失败");
      return "airplane_mod";
    });
  }
}
