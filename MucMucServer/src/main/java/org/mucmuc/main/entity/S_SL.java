package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class S_SL {
    private Integer id_Song;
    private String name_Song;
    private String Singer;
    private Date date_Release;
    private Integer id_SL;
    private String id_User;
    private String name_SL;
    private Date date_SL;
    private String description_SL;
    private Integer Score;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Song);
        list.add(name_Song);
        list.add(Singer);
        list.add(date_Release);
        list.add(id_SL);
        list.add(id_User);
        list.add(name_SL);
        list.add(date_SL);
        list.add(description_SL);
        list.add(Score);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(name_Song!=null)
            list.add(name_Song);
        if(Singer!=null)
            list.add(Singer);
        if(date_Release!=null)
            list.add(date_Release);
//        if(ID_SL!=null)
//            list.add(ID_SL);
        if(id_User!=null)
            list.add(id_User);
        if(name_SL!=null)
            list.add(name_SL);
        if(date_SL!=null)
            list.add(date_SL);
        if(description_SL!=null)
            list.add(description_SL);
        if(Score!=null)
            list.add(Score);
        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[10];
        array[0]=id_Song;
        array[1]=name_Song;
        array[2]=Singer;
        array[3]=date_Release;
        array[4]=id_SL;
        array[5]=id_User;
        array[6]=name_SL;
        array[7]=date_SL;
        array[8]=description_SL;
        array[9]=Score;
        return array;
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

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }
}
