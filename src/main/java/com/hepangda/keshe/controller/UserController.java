package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
import com.hepangda.keshe.util.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("/user")
public class UserController extends GenericController {

  @Autowired
  UserService srv;

  @PostMapping("/add")
  public String create(@RequestBody User user, Model model) {
    return resp(model, () -> srv.add(user), "useradd ok", "useradd false");
  }

  @GetMapping("/list")
  public String list(@RequestParam Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute("biz_user_list", srv.show(ipage));
    return "userlist";
  }

  @GetMapping("/modify/${id}")
  public String modify(@PathVariable("id") long id, Model model) {
    model.addAttribute("biz_modify_user", srv.getById(id));
    return "usermodify";
  }

  @PutMapping("/modify/${id}")
  public String doModify(@PathVariable("id") long id, @RequestBody User user, Model model) {
    return resp(model, () -> srv.update(user), "modifyok");
  }

  @DeleteMapping("/delete/${id}")
  public String doDelete(@PathVariable("id") long id, Model model) {
    return resp(model, () -> srv.deleteById(id), "deleteok");
  }

}
