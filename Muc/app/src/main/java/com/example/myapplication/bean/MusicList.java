package com.example.myapplication.bean;

public class MusicList {
    private String name;

    private int imageID;

    public MusicList(String name, int imageID) {
        this.imageID = imageID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

}
