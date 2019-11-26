package com.example.myapplication.bean;

public class Music{
    private String name;
    private int imageId;

    public Music(String name,int Id){
        this.name = name;
        this.imageId = Id;
    }
    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

}