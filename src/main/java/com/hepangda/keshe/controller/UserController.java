package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
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
public class UserController extends GenericController {

  @Autowired
  UserService srv;

  @GetMapping("/admin/user/create")
  public String pathCreate(Model model) {
    model.addAttribute("active", "user");
    return "usr_creat";
  }

  @GetMapping("/admin/user/modify/{id}")
  public String pathModify(@PathVariable("id") long id, Model model) {
    model.addAttribute("active", "user");
    model.addAttribute(Constants.BIZF_MODIFIER, srv.getById(id));
    return "usr_mod";
  }

  @GetMapping("/admin/user/list")
  public String pathList(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) String keyword, Model model) {
    int ipage = dealPage(page);
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage, keyword));
    model.addAttribute("active", "user");
    model.addAttribute("page_max", srv.getPageMax());
    if (keyword != null && !keyword.isEmpty()) {
      model.addAttribute("not_query", "yes");
    }
    return "usr_list";
  }

  @PostMapping("/api/user/create")
  public String doCreate(@RequestParam Map<String, Object> userMap, Model model) {
    User user = getBeanFromBody(User.class, userMap);
    return resp(model, () -> srv.add(user), i -> pathList(1, null, model), i -> {
      model.addAttribute("active", "user");
      model.addAttribute(Constants.GBF_BIZMSG, "创建失败");
      return "usr_creat";
    });
  }

  @PostMapping("/api/user/delete")
  public String doDelete(@RequestParam long id, Model model) {
    model.addAttribute("active", "user");
    return resp(model, () -> srv.deleteById(id), () -> pathList(1, null, model));
  }

  @PostMapping("/api/user/modify/{id}")
  public String doModify(@PathVariable("id") long id, @RequestParam Map<String, Object> userMap,
      Model model) {
    User user = getBeanFromBody(User.class, userMap);
    model.addAttribute("active", "user");
    return resp(model, () -> srv.update(user), i -> pathList(1, null, model), i -> {
      model.addAttribute("active", "user");
      model.addAttribute(Constants.GBF_BIZMSG, "修改失败");
      return "usr_mod";
    });
  }
}
