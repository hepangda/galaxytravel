package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors
public class Order {

  private Long id;

  private Long userId;

  private Long flightId;

  private Integer clazz;

  private Long cost;

  private Integer type;

  private Integer row;

  private Integer col;
}
