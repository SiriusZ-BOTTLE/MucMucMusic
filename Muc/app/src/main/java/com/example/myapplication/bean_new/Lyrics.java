package com.example.myapplication.bean_new;

import java.util.ArrayList;
import java.util.List;

public class Lyrics {
    private Integer ID_Lyrics;
    private Integer ID_Song;
    private String Content_Lyrics;
    private Boolean Flag_Pure_Lyrics;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Lyrics);
        list.add(ID_Song);
        list.add(Content_Lyrics);
        list.add(Flag_Pure_Lyrics);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Lyrics!=null)
//            list.add(ID_Lyrics);
        if(ID_Song!=null)
            list.add(ID_Song);
        if (Content_Lyrics!=null)
            list.add(Content_Lyrics);
        if (Flag_Pure_Lyrics!=null)
            list.add(Flag_Pure_Lyrics);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[4];
        array[0]=ID_Lyrics;
        array[1]=ID_Song;
        array[2]=Content_Lyrics;
        array[3]=Flag_Pure_Lyrics;
        return array;
    }

    public Integer getID_Lyrics() {
        return ID_Lyrics;
    }

    public void setID_Lyrics(Integer ID_Lyrics) {
        this.ID_Lyrics = ID_Lyrics;
    }

    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }

    public String getContent_Lyrics() {
        return Content_Lyrics;
    }

    public void setContent_Lyrics(String content_Lyrics) {
        Content_Lyrics = content_Lyrics;
    }

    public Boolean getFlag_Pure_Lyrics() {
        return Flag_Pure_Lyrics;
    }

    public void setFlag_Pure_Lyrics(Boolean flag_Pure_Lyrics) {
        Flag_Pure_Lyrics = flag_Pure_Lyrics;
    }
}
