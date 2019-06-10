package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Airport {

  private Long id;

  private String cityName;

  private String portName;

  private Integer type;
}
