package com.hepangda.keshe.controller;

import com.hepangda.keshe.dto.ResponseDTO;
import com.hepangda.keshe.exception.BizStatusCode;
import com.hepangda.keshe.mapper.ClientMapper;
import com.hepangda.keshe.model.Client;
import com.hepangda.keshe.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  ClientMapper userMapper;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseDTO<Client> get() {
    return CodeUtils.response(BizStatusCode.SqlErrorUser, () -> userMapper.getById(1));
  }


}
