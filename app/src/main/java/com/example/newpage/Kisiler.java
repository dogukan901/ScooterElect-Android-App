package com.example.newpage;

import java.io.Serializable;

public class Kisiler implements Serializable {

    private int tc;
    private String isim;
    private double boy;


    public Kisiler() {
    }

    public Kisiler(int tc, String isim, double boy) {
        this.tc = tc;
        this.isim = isim;
        this.boy = boy;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public double getBoy() {
        return boy;
    }

    public void setBoy(double boy) {
        this.boy = boy;
    }
}
