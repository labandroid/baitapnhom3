package com.example.bai5.Model;

public class CustomItem {
    private int resource;
    private String name;

    public CustomItem(int resource, String name) {
        this.resource = resource;
        this.name = name;
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
}
