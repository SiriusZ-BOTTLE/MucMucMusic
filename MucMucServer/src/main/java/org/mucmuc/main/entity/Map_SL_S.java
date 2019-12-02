package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Map_SL_S {
    private Integer ID_SL;
    private Integer ID_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_SL);
        list.add(ID_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(ID_SL!=null)
            list.add(ID_SL);
        if(ID_Song!=null)
            list.add(ID_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[2];
        array[0]=ID_SL;
        array[1]=ID_Song;
        return array;
    }
    public Integer getID_SL() {
        return ID_SL;
    }

    public void setID_SL(Integer ID_SL) {
        this.ID_SL = ID_SL;
    }

    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }
}
