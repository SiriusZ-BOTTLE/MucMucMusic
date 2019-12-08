package org.mucmuc.main.entity;

import javax.xml.crypto.dsig.SignedInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tag_Song {
    private Integer id_Tag;
    private String name_Tag;
    private Integer id_Song;
    private String name_Song;
    private String singer_Song;
    private Date releaseDate_So;
    private String file_Song;
    private String iconFile_Song;
    private Double score_Song;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Tag);
        list.add(name_Tag);
        list.add(id_Song);
        list.add(name_Song);
        list.add(singer_Song);
        list.add(releaseDate_So);
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

//        if(ID_Tag!=null)
//            list.add(ID_Tag);
        if(name_Tag!=null)
            list.add(name_Tag);
//        if(ID_Song!=null)
//            list.add(ID_Song);
        if(name_Song!=null)
            list.add(name_Song);
        if(singer_Song!=null)
            list.add(singer_Song);
        if(releaseDate_So!=null)
            list.add(releaseDate_So);
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
        Object[] array=new Object[9];
        array[0]=id_Tag;
        array[1]=name_Tag;
        array[2]=id_Song;
        array[3]=name_Song;
        array[4]=singer_Song;
        array[5]=releaseDate_So;
        array[6]=file_Song;
        array[7]=iconFile_Song;
        array[8]=score_Song;
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

    public Date getReleaseDate_So() {
        return releaseDate_So;
    }

    public void setReleaseDate_So(Date releaseDate_So) {
        this.releaseDate_So = releaseDate_So;
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
