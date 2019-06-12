package com.hepangda.keshe.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.keshe.model.User;
import com.hepangda.keshe.service.UserService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class LoginController extends GenericController {

  @Autowired
  UserService srv;

  @GetMapping
  public String pathRoot(HttpSession session) {
    if (session.getAttribute(Constants.SESSION_USER) == null) {
      return "login";
    }
    return "index";
  }

  @GetMapping("/login")
  public String pathLogin() { return "login";  }

  @GetMapping("/register")
  public String pathRegister() {
    return "register";
  }

  @PostMapping("/api/login")
  public String login(@RequestParam Map<String, Object> userMap, Model model) {
    User user = getBeanFromBody(User.class, userMap);
    return resp(model, () -> srv.login(user), "index", "login");
  }

  @PostMapping("/api/register")
  public String register(@RequestParam Map<String, Object> userMap, Model model) {
    User user = getBeanFromBody(User.class, userMap);
    return resp(model, () -> srv.register(user), "login", "register");
  }
}
