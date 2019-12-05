package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Song {
    private Integer id_Song;
    private String name_Song;
    private String singer_Song;
    private Date releaseDate_Song;

    private String file_Song;
    private String iconFile_Song;

    private Double Score;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Song);
        list.add(name_Song);
        list.add(singer_Song);
        list.add(releaseDate_Song);

        list.add(file_Song);

        list.add(Score);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
//    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(name_Song!=null)
            list.add(name_Song);
        if (singer_Song!=null)
            list.add(singer_Song);
        if (releaseDate_Song!=null)
            list.add(releaseDate_Song);

        if (file_Song !=null)
            list.add(file_Song);

        if (Score!=null)
            list.add(Score);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[6];
        array[0]=id_Song;
        array[1]=name_Song;
        array[2]=singer_Song;
        array[3]=releaseDate_Song;

        array[4]= file_Song;

        array[5]=Score;
        return array;
    }

    public String getIconFile_Song() {
        return iconFile_Song;
    }

    public void setIconFile_Song(String iconFile_Song) {
        this.iconFile_Song = iconFile_Song;
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

    public String getSinger_Song() {
        return singer_Song;
    }

    public void setSinger_Song(String singer_Song) {
        this.singer_Song = singer_Song;
    }

    public Date getReleaseDate_Song() {
        return releaseDate_Song;
    }

    public void setReleaseDate_Song(Date releaseDate_Song) {
        this.releaseDate_Song = releaseDate_Song;
    }

    public String getFile_Song() {
        return file_Song;
    }

    public void setFile_Song(String file_Song) {
        this.file_Song = file_Song;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }


}
