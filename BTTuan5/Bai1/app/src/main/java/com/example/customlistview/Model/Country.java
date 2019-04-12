package com.example.customlistview.Model;

public class Country {
    private int resource;
    private String name;
    private String danSo;

    public Country(int resource, String name,String danSo) {
        this.resource = resource;
        this.name = name;
        this.danSo = danSo;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDanSo() {
        return danSo;
    }

    public void setDanSo(String danSo) {
        this.danSo = danSo;
    }
}
