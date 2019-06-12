package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors
public class Flight {

  private Long id;

  private Long airlineId;

  private Long airplaneId;

  private String name;

  private Long scheTime;

  private Long firstClassPrice;

  private Long secondClassPrice;
}
