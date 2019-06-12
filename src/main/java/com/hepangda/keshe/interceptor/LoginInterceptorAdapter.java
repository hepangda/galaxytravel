package com.hepangda.keshe.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class LoginInterceptorAdapter extends WebMvcConfigurerAdapter {

  @Autowired
  LoginInterceptor interceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(interceptor)
        .addPathPatterns("/admin/**")
        .addPathPatterns("/user/**")
        .addPathPatterns("/index");

    super.addInterceptors(registry);
  }
}

