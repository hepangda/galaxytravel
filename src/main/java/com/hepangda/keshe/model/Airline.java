package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Airline {

  private Long id;

  private String name;

  private Long sourcePortId;

  private Long destPortId;

  private Integer type;
}
