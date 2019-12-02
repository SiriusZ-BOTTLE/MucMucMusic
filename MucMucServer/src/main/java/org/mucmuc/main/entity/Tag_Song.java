package org.mucmuc.main.entity;

import javax.xml.crypto.dsig.SignedInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tag_Song {
    private Integer ID_Tag;
    private String Name_Tag;
    private Integer ID_Song;
    private String Name_Song;
    private String Singer;
    private Date Date_Release;
    private String Content_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Tag);
        list.add(Name_Tag);
        list.add(ID_Song);
        list.add(Name_Song);
        list.add(Singer);
        list.add(Date_Release);
        list.add(Content_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Tag!=null)
//            list.add(ID_Tag);
        if(Name_Tag!=null)
            list.add(Name_Tag);
//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(Name_Song!=null)
            list.add(Name_Song);
        if(Singer!=null)
            list.add(Singer);
        if(Date_Release!=null)
            list.add(Date_Release);
        if(Content_Song!=null)
            list.add(Content_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[7];
        array[0]=ID_Tag;
        array[1]=Name_Tag;
        array[2]=ID_Song;
        array[3]=Name_Song;
        array[4]=Singer;
        array[5]=Date_Release;
        array[6]=Content_Song;
        return array;
    }



    public Integer getID_Tag() {
        return ID_Tag;
    }

    public void setID_Tag(Integer ID_Tag) {
        this.ID_Tag = ID_Tag;
    }

    public String getName_Tag() {
        return Name_Tag;
    }

    public void setName_Tag(String name_Tag) {
        Name_Tag = name_Tag;
    }

    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }

    public String getName_Song() {
        return Name_Song;
    }

    public void setName_Song(String name_Song) {
        Name_Song = name_Song;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public Date getDate_Release() {
        return Date_Release;
    }

    public void setDate_Release(Date date_Release) {
        Date_Release = date_Release;
    }

    public String getContent_Song() {
        return Content_Song;
    }

    public void setContent_Song(String content_Song) {
        Content_Song = content_Song;
    }
}
