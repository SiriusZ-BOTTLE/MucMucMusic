package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Map_S_T {
    private Integer id_Song;
    private Integer id_Tag;
    private Integer Num;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Song);
        list.add(id_Tag);
        list.add(Num);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();


        if(Num!=null)
            list.add(Num);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[3];
        array[0]=id_Song;
        array[1]=id_Tag;
        array[2]=Num;
        return array;
    }

    public Integer getId_Song() {
        return id_Song;
    }

    public void setId_Song(Integer id_Song) {
        this.id_Song = id_Song;
    }

    public Integer getId_Tag() {
        return id_Tag;
    }

    public void setId_Tag(Integer id_Tag) {
        this.id_Tag = id_Tag;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }
}
