package com.example.myapplication.bean_new;

import java.util.ArrayList;
import java.util.List;

public class Map_SL_S {
    private Integer id_SL;
    private Integer id_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_SL);
        list.add(id_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(id_SL!=null)
            list.add(id_SL);
        if(id_Song!=null)
            list.add(id_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[2];
        array[0]=id_SL;
        array[1]=id_Song;
        return array;
    }

    public Integer getId_SL() {
        return id_SL;
    }

    public void setId_SL(Integer id_SL) {
        this.id_SL = id_SL;
    }

    public Integer getId_Song() {
        return id_Song;
    }

    public void setId_Song(Integer id_Song) {
        this.id_Song = id_Song;
    }
}
