package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private Integer ID_Tag;
    private String Name_Tag;


    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Tag);
        list.add(Name_Tag);
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


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[2];
        array[0]=ID_Tag;
        array[1]=Name_Tag;
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
}
