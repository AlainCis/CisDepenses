package com.example.alaincis.cisdepenses;

/**
 * Created by AlainCis on 18/09/2019.
 */

public class depense {
    private String des;
    private String mont;
    private String urg;
    private String del;
    private String rel;

    public depense(String des, String mont, String urg, String del, String rel) {
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMont() {
        return mont;
    }

    public void setMont(String mont) {
        this.mont = mont;
    }

    public String getUrg() {
        return urg;
    }

    public void setUrg(String urg) {
        this.urg = urg;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}


