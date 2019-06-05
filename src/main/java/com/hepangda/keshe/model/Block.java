package com.hepangda.keshe.model;
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Block {
    private String bid;

    private String bclientid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBclientid() {
        return bclientid;
    }

    public void setBclientid(String bclientid) {
        this.bclientid = bclientid;
    }


}
