package com.hepangda.keshe.model;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Flight {

  private Long id;

  private Long airlineId;

  private Long airplaneId;

  private String name;

  private Long scheTime;

  private Long firstClassPrice;

  private Long secondClassPrice;
}
