package com.example.myapplication.bean;

import java.util.List;

public class Comment {
    private String details;
    private String author_name;
    private Music music;

    public Comment(String detail,String name,Music muc) {
        this.details = detail;
        this.author_name = name;
        this.music = muc;
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
