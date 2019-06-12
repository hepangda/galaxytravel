package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors
public class User {

  private Long id;

  private String username;

  private String pwd;

  private Integer type;

  private String realname;

  private String phone;
}
