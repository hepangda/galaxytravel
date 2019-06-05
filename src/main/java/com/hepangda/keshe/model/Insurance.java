package com.hepangda.keshe.model;
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Insurance {
    private String insid;
    private String owner;
    private double price;

    public String getInsid() {
        return insid;
    }

    public void setInsid(String insid) {
        this.insid = insid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
