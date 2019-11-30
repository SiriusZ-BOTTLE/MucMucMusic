package com.example.myapplication.bean;

public class Music{
    private String name;
    private int imageId;
    private String author;

    public Music(String name,int Id,String author){
        this.name = name;
        this.imageId = Id;
        this.author = author;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getAuthor(){
        return author;
    }

}