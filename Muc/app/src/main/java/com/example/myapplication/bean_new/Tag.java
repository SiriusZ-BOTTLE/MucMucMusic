package com.example.myapplication.bean_new;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private Integer id_Tag;
    private String name_Tag;


    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Tag);
        list.add(name_Tag);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Tag!=null)
//            list.add(ID_Tag);
        if(name_Tag!=null)
            list.add(name_Tag);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[2];
        array[0]=id_Tag;
        array[1]=name_Tag;
        return array;
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
