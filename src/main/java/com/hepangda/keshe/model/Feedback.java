package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors
public class Feedback {

  private Long id;

  private Long orderId;

  private String message;
}
