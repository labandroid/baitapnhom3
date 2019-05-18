package com.example.baitap4m;

public class Model {
    int icon;
    String title;

    public Model(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public Model() {}

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Model{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
