package com.hepangda.keshe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
@AllArgsConstructor
public class Movie {

  private int id;

  private String name;

  private int type;

  private int status;

  private int religon;

  private String description;

  private String time;

  private String image;
}
