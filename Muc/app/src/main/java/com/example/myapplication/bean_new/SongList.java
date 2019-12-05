package com.example.myapplication.bean_new;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongList implements Serializable {
    private Integer id_SL;
    private String id_User;
    private String name_SL;
    private Date date_SL;
    private String description_SL;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_SL);
        list.add(id_User);
        list.add(name_SL);
        list.add(date_SL);
        list.add(description_SL);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_SL!=null)
//            list.add(ID_SL);
        if(id_User!=null)
            list.add(id_User);
        if(name_SL!=null)
            list.add(name_SL);
        if (date_SL!=null)
            list.add(date_SL);
        if (description_SL!=null)
            list.add(description_SL);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[5];
        array[0]=id_SL;
        array[1]=id_User;
        array[2]=name_SL;
        array[3]=date_SL;
        array[4]=description_SL;
        return array;
    }

    public Integer getId_SL() {
        return id_SL;
    }

    public void setId_SL(Integer id_SL) {
        this.id_SL = id_SL;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getName_SL() {
        return name_SL;
    }

    public void setName_SL(String name_SL) {
        this.name_SL = name_SL;
    }

    public Date getDate_SL() {
        return date_SL;
    }

    public void setDate_SL(Date date_SL) {
        this.date_SL = date_SL;
    }

    public String getDescription_SL() {
        return description_SL;
    }

    public void setDescription_SL(String description_SL) {
        this.description_SL = description_SL;
    }

}
