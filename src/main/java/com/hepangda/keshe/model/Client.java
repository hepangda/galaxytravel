package com.hepangda.keshe.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Client {

  private String clid;
  private String name;
  private String password;
  private String idnumber;
  private int gender;
  private int age;
  private int vip;

  public String getClid() {
    return clid;
  }

  public void setClid(String clid) {
    this.clid = clid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIdnumber() {
    return idnumber;
  }

  public void setIdnumber(String idnumber) {
    this.idnumber = idnumber;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getVip() {
    return vip;
  }

  public void setVip(int vip) {
    this.vip = vip;
  }
}
