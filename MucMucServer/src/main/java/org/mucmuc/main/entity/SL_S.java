package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SL_S {
    private Integer id_SL;
    private String name_SL;
    private Date date_SL;
    private Integer id_Song;
    private String name_Song;
    private String Singer;
    private Date date_Release;
    private String content_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_SL);
        list.add(name_SL);
        list.add(date_SL);
        list.add(id_Song);
        list.add(name_SL);
        list.add(Singer);
        list.add(date_Release);
        list.add(content_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_SL!=null)
//            list.add(ID_SL);
        if(name_SL!=null)
            list.add(name_SL);
        if(date_SL!=null)
            list.add(date_SL);
//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(name_SL!=null)
            list.add(name_SL);
        if(Singer!=null)
            list.add(Singer);
        if(date_Release!=null)
            list.add(date_Release);
        if(content_Song!=null)
            list.add(content_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[9];
        array[0]=id_SL;
        array[1]=name_SL;
        array[2]=date_SL;
        array[3]=id_Song;
        array[4]=name_SL;
        array[5]=Singer;
        array[6]=date_Release;
        array[7]=content_Song;
        return array;
    }


    public Integer getId_SL() {
        return id_SL;
    }

    public void setId_SL(Integer id_SL) {
        this.id_SL = id_SL;
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
}
