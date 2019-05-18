package com.example.bai3.Model;

public class CauThu {
    private int resource;
    private int resource2;
    private String name;
    private String des;

    public CauThu(int resource, String name, String description,int resource2) {
        this.resource = resource;
        this.name = name;
        this.des = description;
        this.resource2 = resource2;
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

    public int getResource2() {
        return resource2;
    }

    public void setResource2(int resource2) {
        this.resource2 = resource2;
    }
}
