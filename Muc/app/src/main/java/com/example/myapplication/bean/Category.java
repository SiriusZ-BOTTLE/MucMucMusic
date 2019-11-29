package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Music> musicList;

    public Category(String name,List<Music> musiclist){
        this.name = name;
        this.musicList = musiclist;
    }

    public String getName(){
        return name;
    }

    public List<Music> getMusicList(){
        return  musicList;
    }

}
