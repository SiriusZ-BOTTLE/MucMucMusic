package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Song_Tag {
    private Integer id_Song;
    private String name_Song;
    private String Singer;
    private Date date_Release;
    private String content_Song;
    private Integer id_Tag;
    private String name_Tag;
    private Integer Score;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Song);
        list.add(name_Song);
        list.add(Singer);
        list.add(date_Release);
        list.add(content_Song);
        list.add(id_Tag);
        list.add(name_Tag);
        list.add(Score);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    //  @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(name_Song!=null)
            list.add(name_Song);
        if(Singer!=null)
            list.add(Singer);
        if(date_Release!=null)
            list.add(date_Release);
        if(content_Song!=null)
            list.add(content_Song);
        if(id_Tag!=null)
            list.add(id_Tag);
        if(name_Tag!=null)
            list.add(name_Tag);
        if(Score!=null)
            list.add(Score);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[8];
        array[0]=id_Song;
        array[1]=name_Song;
        array[2]=Singer;
        array[3]=date_Release;
        array[4]=content_Song;
        array[5]=id_Tag;
        array[6]=name_Tag;
        array[7]=Score;
        return array;
    }

    public Integer getId_Song() {
        return id_Song;
    }

    public void setId_Song(Integer id_Song) {
        this.id_Song = id_Song;
    }

    public String getName_Song() {
        return name_Song;
    }

    public void setName_Song(String name_Song) {
        this.name_Song = name_Song;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public Date getDate_Release() {
        return date_Release;
    }

    public void setDate_Release(Date date_Release) {
        this.date_Release = date_Release;
    }

    public String getContent_Song() {
        return content_Song;
    }

    public void setContent_Song(String content_Song) {
        this.content_Song = content_Song;
    }

    public Integer getId_Tag() {
        return id_Tag;
    }

    public void setId_Tag(Integer id_Tag) {
        this.id_Tag = id_Tag;
    }

    public String getName_Tag() {
        return name_Tag;
    }

    public void setName_Tag(String name_Tag) {
        this.name_Tag = name_Tag;
    }
}
