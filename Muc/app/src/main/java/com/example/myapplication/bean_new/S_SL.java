package com.example.myapplication.bean_new;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class S_SL {
    private Integer ID_Song;
    private String Name_Song;
    private String Singer;
    private Date Date_Release;
    private Integer ID_SL;
    private String ID_User;
    private String Name_SL;
    private Date Date_SL;
    private String Description_SL;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Song);
        list.add(Name_Song);
        list.add(Singer);
        list.add(Date_Release);
        list.add(ID_SL);
        list.add(ID_User);
        list.add(Name_SL);
        list.add(Date_SL);
        list.add(Description_SL);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(Name_Song!=null)
            list.add(Name_Song);
        if(Singer!=null)
            list.add(Singer);
        if(Date_Release!=null)
            list.add(Date_Release);
//        if(ID_SL!=null)
//            list.add(ID_SL);
        if(ID_User!=null)
            list.add(ID_User);
        if(Name_SL!=null)
            list.add(Name_SL);
        if(Date_SL!=null)
            list.add(Date_SL);
        if(Description_SL!=null)
            list.add(Description_SL);
        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[9];
        array[0]=ID_Song;
        array[1]=Name_Song;
        array[2]=Singer;
        array[3]=Date_Release;
        array[4]=ID_SL;
        array[5]=ID_User;
        array[6]=Name_SL;
        array[7]=Date_SL;
        array[8]=Description_SL;
        return array;
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

    public Integer getID_SL() {
        return ID_SL;
    }

    public void setID_SL(Integer ID_SL) {
        this.ID_SL = ID_SL;
    }

    public String getID_User() {
        return ID_User;
    }

    public void setID_User(String ID_User) {
        this.ID_User = ID_User;
    }

    public String getName_SL() {
        return Name_SL;
    }

    public void setName_SL(String name_SL) {
        Name_SL = name_SL;
    }

    public Date getDate_SL() {
        return Date_SL;
    }

    public void setDate_SL(Date date_SL) {
        Date_SL = date_SL;
    }

    public String getDescription_SL() {
        return Description_SL;
    }

    public void setDescription_SL(String description_SL) {
        Description_SL = description_SL;
    }
}
