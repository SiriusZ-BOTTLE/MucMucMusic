package com.example.myapplication.bean_new.InteractionEntity;

public class songlistnew {
    private String name;
    private int imageID;

    public songlistnew(String name, int imageID) {
        this.name = name;
    this.imageID=imageID;
    }

    public String getName() {
        return name;
    }
    public int getImageID(){return  imageID;}

}
