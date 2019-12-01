package com.example.myapplication.bean;

public class User {
    private int imageId;
    private String NickName;
    private int Level;
    private String Password;
    private String UserId;
    public User(int imageId,String NickName,int Level,String  Password,String UserId){
        this.imageId=imageId;
        this.NickName=NickName;
        this.Level=Level;
        this.Password=Password;
        this.UserId=UserId;
    }


    public int getImageId() {
        return imageId;
    }

    public String getNickName() {
        return NickName;
    }

    public int getLevel() {
        return Level;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserId() {
        return UserId;
    }
}
