package com.example.myapplication.bean_new;

import java.util.ArrayList;
import java.util.List;

public class Map_S_T {
    private Integer ID_Song;
    private Integer ID_Tag;
    private Integer Num;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Song);
        list.add(ID_Tag);
        list.add(Num);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if (ID_Song!=null)
            list.add(ID_Song);
        if(ID_Tag!=null)
            list.add(ID_Tag);
        if(Num!=null)
            list.add(Num);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[3];
        array[0]=ID_Song;
        array[1]=ID_Tag;
        array[2]=Num;
        return array;
    }
    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }

    public Integer getID_Tag() {
        return ID_Tag;
    }

    public void setID_Tag(Integer ID_Tag) {
        this.ID_Tag = ID_Tag;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }
}
