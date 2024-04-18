package com.example.practice8.DATA.Model;

public class DataListRecycler {
    String name;
    int image;
    public DataListRecycler(String name, int image){
        this.name = name;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImage(int image) {
        this.image = image;
    }
}
