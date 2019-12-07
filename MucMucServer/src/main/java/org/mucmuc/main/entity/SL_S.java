package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SL_S {
    private Integer id_SL;
    private String id_User;
    private String name_SL;
    private Date date_SL;
    private String description_SL;
    private Integer id_Song;
    private String name_Song;
    private String singer_Song;
    private Date releaseDate_Song;
    private String file_Song;
    private String iconFile_Song;
    private Double score_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_SL);
        list.add(id_User);
        list.add(name_SL);
        list.add(date_SL);
        list.add(description_SL);
        list.add(id_Song);
        list.add(name_Song);
        list.add(singer_Song);
        list.add(releaseDate_Song);
        list.add(file_Song);
        list.add(iconFile_Song);
        list.add(score_Song);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    //  @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_SL!=null)
//            list.add(ID_SL);
        if(id_User!=null)
            list.add(id_User);
        if(name_SL!=null)
            list.add(name_SL);
        if(date_SL!=null)
            list.add(date_SL);
//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(description_SL!=null)
            list.add(description_SL);
//        if(id_Song!=null)
//            list.add(id_Song);
        if(name_Song!=null)
            list.add(name_Song);
        if(singer_Song!=null)
            list.add(singer_Song);
        if(releaseDate_Song!=null)
            list.add(releaseDate_Song);
        if(file_Song!=null)
            list.add(file_Song);
        if(iconFile_Song!=null)
            list.add(iconFile_Song);
        if(score_Song!=null)
            list.add(score_Song);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[12];
        array[0] = id_SL;
        array[1] = id_User;
        array[2] = name_SL;
        array[3] = date_SL;
        array[4] = description_SL;
        array[5] = id_Song;
        array[6] = name_Song;
        array[7] = singer_Song;
        array[8] = releaseDate_Song;
        array[9] = file_Song;
        array[10] = iconFile_Song;
        array[11] = score_Song;
        return array;
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

    public String getIconFile_Song() {
        return iconFile_Song;
    }

    public void setIconFile_Song(String iconFile_Song) {
        this.iconFile_Song = iconFile_Song;
    }

    public Double getScore_Song() {
        return score_Song;
    }

    public void setScore_Song(Double score_Song) {
        this.score_Song = score_Song;
    }
}
