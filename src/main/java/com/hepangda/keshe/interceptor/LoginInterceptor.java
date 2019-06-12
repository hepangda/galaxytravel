package com.hepangda.keshe.interceptor;

import com.hepangda.keshe.model.User;
import com.hepangda.keshe.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute(Constants.SESSION_USER);

    if (!privilege(loginUser, request.getRequestURI())) {
      response.sendRedirect("/login");
      return true;
    } else if (request.getRequestURI().startsWith("/login")) {
      response.sendRedirect("/index");
      return true;
    }

    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
  }

  private String[] FORBIDDEN_PREFIX = {
      "/api", "/admin", "/user"
  };

  private boolean privilege(User user, String requestUri) {
    if (user == null) {
      return false;
    }

    for (String i : FORBIDDEN_PREFIX) {
      if (requestUri.startsWith(i)) {
        return false;
      }
    }

    return true;
  }
}
