package com.example.myapplication.bean;

public class MusicList {
    private String name;
    private int size;
    private String author;
    private int imageID;

    public MusicList(String name, int imageID,int size,String author) {
        this.imageID = imageID;
        this.name = name;
        this.size = size;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }
    public int getSize(){
        return  size;
    }
    public String getAuthor(){
        return author;
    }
}
