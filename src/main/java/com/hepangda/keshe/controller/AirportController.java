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
  public String pathCreate(Model model) {
    model.addAttribute("active", "airport");
    return "airport_creat";
  }

  @GetMapping("/admin/airport/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    model.addAttribute("active", "airport");
    return "airport_mod";
  }

  @GetMapping("/admin/airport/list")
  public String pathList(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) String keyword, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, keyword));
    model.addAttribute("active", "airport");
    model.addAttribute("page_max", srv.getPageMax());
    if (keyword != null && !keyword.isEmpty()) {
      model.addAttribute("not_query", "yes");
    }
    return "airport_list";
  }

  @PostMapping("/api/airport/create")
  public String doCreate(@RequestParam Map<String, Object> airportMap, Model model) {
    Airport airport = getBeanFromBody(Airport.class, airportMap);
    return resp(model, () -> srv.add(airport), i -> pathList(1, null, model), i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "创建失败");
      model.addAttribute("active", "airport");
      return "airport_creat";
    });
  }

  @PostMapping("/api/airport/delete")
  public String doDelete(@RequestParam long id, Model model) {
    model.addAttribute("active", "airport");
    return resp(model, () -> srv.deleteById(id), () -> pathList(1, null, model));
  }

  @PostMapping("/api/airport/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> airportMap,
      Model model) {
    Airport airport = getBeanFromBody(Airport.class, airportMap);
    model.addAttribute("active", "airport");
    return resp(model, () -> srv.update(airport), i -> pathList(1, null, model), i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "修改失败");
      return "airport_mod";
    });
  }
}
