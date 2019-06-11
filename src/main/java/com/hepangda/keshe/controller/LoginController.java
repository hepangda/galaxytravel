package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
import com.hepangda.keshe.util.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@Controller
public class LoginController extends GenericController {

  @Autowired
  UserService srv;

  @GetMapping("/login")
  public String pathLogin() {
    return "login";
  }

  @GetMapping("/register")
  public String pathRegister() {
    return "register";
  }

  @PostMapping("/api/login")
  public String login(@RequestBody User user, Model model) {
    return resp(model, () -> srv.login(user), "loginok", "loginfailed");
  }

  @PostMapping("/api/register")
  public String register(@RequestBody User user, Model model) {
    return resp(model, () -> srv.register(user), "reigsterok", "registerfailed");
  }
}
