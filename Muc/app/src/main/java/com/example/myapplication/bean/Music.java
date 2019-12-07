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
    public Music(){

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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