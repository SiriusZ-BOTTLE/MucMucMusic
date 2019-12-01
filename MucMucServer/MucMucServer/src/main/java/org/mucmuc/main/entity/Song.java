package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Song {
    private Integer ID_Song;
    private String Name_Song;
    private String Singer_Song;
    private Date ReleaseDate_Song;
    private String FileURL_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Song);
        list.add(Name_Song);
        list.add(Singer_Song);
        list.add(ReleaseDate_Song);
        list.add(FileURL_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(ID_Song!=null)
            list.add(ID_Song);
        if(Name_Song!=null)
            list.add(Name_Song);
        if (Singer_Song!=null)
            list.add(Singer_Song);
        if (ReleaseDate_Song!=null)
            list.add(ReleaseDate_Song);
        if (FileURL_Song!=null)
            list.add(FileURL_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[5];
        array[0]=ID_Song;
        array[1]=Name_Song;
        array[2]=Singer_Song;
        array[3]=ReleaseDate_Song;
        array[4]=FileURL_Song;
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

    public String getSinger_Song() {
        return Singer_Song;
    }

    public void setSinger_Song(String singer_Song) {
        Singer_Song = singer_Song;
    }

    public Date getReleaseDate_Song() {
        return ReleaseDate_Song;
    }

    public void setReleaseDate_Song(Date releaseDate_Song) {
        ReleaseDate_Song = releaseDate_Song;
    }

    public String getFileURL_Song() {
        return FileURL_Song;
    }

    public void setFileURL_Song(String fileURL_Song) {
        FileURL_Song = fileURL_Song;
    }
}
