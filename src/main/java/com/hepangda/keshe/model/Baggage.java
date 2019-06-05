package com.hepangda.keshe.model;
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Baggage {
    private String bgid;

    public String getBgid() {
        return bgid;
    }

    public void setBgid(String bgid) {
        this.bgid = bgid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private String owner;//clid
    private String weight;
    private Double price;



}
