package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
import com.hepangda.keshe.util.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends GenericController {

  @Autowired
  UserService srv;

  @PostMapping("/login")
  public String login(@RequestBody User user, Model model) {
    return resp(model, () -> srv.login(user), "loginok", "loginfailed");
  }

  @PostMapping("/register")
  public String register(@RequestBody User user, Model model) {
    return resp(model, () -> srv.register(user), "reigsterok", "registerfailed");
  }
}
