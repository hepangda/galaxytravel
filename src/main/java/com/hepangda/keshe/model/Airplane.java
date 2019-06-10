package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Airplane {

  private Long id;

  private String name;

  private Integer type;

  private Integer rows;

  private Integer cols;

  private Integer firstClassRows;
}
