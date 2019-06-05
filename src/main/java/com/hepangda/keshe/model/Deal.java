package com.hepangda.keshe.model;
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Deal {
    private String did;
    private String owner;//clid;
    private String tkid;
    private int status;//是否飞出

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTkid() {
        return tkid;
    }

    public void setTkid(String tkid) {
        this.tkid = tkid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
