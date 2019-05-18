package com.example.bai2.Model;

public class RauTraiCay {
    private int resource;
    private String name;
    private String des;

    public RauTraiCay(int resource, String name, String description) {
        this.resource = resource;
        this.name = name;
        this.des = description;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
