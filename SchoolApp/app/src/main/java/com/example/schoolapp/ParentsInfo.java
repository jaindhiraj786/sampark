package com.example.schoolapp;

import java.io.Serializable;

public class ParentsInfo implements Serializable {
    private int image;
    String name;

    public ParentsInfo(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
