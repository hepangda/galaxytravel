package com.hepangda.keshe.model;
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class City {
    private String cid;
    private String name;
    private String lid;
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }


}
