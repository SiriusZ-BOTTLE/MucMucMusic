package com.example.myapplication.bean;

import com.example.myapplication.bean_new.User;

import java.util.List;

public class Comment {
    private String details;
    private String author_name;
    private Music music;
    private com.example.myapplication.bean_new.User user;
    private int likes;
    private float score;

    public Comment(String detail,String name,Music muc) {
        this.details = detail;
        this.author_name = name;
        this.music = muc;
        this.likes = 0;
    }
    public Comment() {
        this.likes = 0;
    }
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setUser(com.example.myapplication.bean_new.User user) {
        this.user = user;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public int getLikes() {
        return likes;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getDetails(){
        return details;
    }

    public String getAuthor_name(){
        return  author_name;
    }

    public Music getMusic() {
        return music;
    }
}
