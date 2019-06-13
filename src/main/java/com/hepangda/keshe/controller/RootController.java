package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class RootController extends GenericController {

  @Autowired
  UserService srv;

  @GetMapping("/")
  public String pathRoot(HttpSession session) {
    User user = (User) session.getAttribute(Constants.SESSION_USER);
    if (user == null) {
      return "login";
    }
    if (user.getType() == 0) {
      return "index";
    } else {
      return "gotoadmin";
    }
  }

  @GetMapping("/forbidden")
  public String pathForbidden() {
    return "forbidden";
  }

  @GetMapping("/login")
  public String pathLogin() {
    return "login";
  }

  @GetMapping("/register")
  public String pathRegister() {
    return "register";
  }

  @GetMapping("/logout")
  public String pathLogout(HttpSession session) {
    session.invalidate();
    return "/";
  }

  @PostMapping("/api/login")
  public String login(@RequestParam Map<String, Object> userMap, Model model, HttpSession session) {
    User user = getBeanFromBody(User.class, userMap);
    return resp(model, () -> srv.login(user), i -> {
      session.setAttribute(Constants.SESSION_USER, i);
      if (i.getType() == 1) {
        return "gotoadmin";
      }
      return "index";
    }, i -> {
      model.addAttribute(Constants.GBF_BIZMSG, "用户名密码错误，请重试");
      return "login";
    });
  }

  @PostMapping("/api/register")
  public String register(@RequestParam Map<String, Object> userMap, Model model) {
    User user = getBeanFromBody(User.class, userMap);
    return resp(model, () -> srv.register(user), "login", "register");
  }
}
