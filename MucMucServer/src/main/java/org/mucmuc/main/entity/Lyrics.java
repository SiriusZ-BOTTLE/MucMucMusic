package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Lyrics {
    private Integer id_Lyrics;
    private Integer id_Song;
    private String content_Lyrics;
    private Boolean flag_Pure_Lyrics;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Lyrics);
        list.add(id_Song);
        list.add(content_Lyrics);
        list.add(flag_Pure_Lyrics);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Lyrics!=null)
//            list.add(ID_Lyrics);
        if(id_Song!=null)
            list.add(id_Song);
        if (content_Lyrics!=null)
            list.add(content_Lyrics);
        if (flag_Pure_Lyrics!=null)
            list.add(flag_Pure_Lyrics);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[4];
        array[0]=id_Lyrics;
        array[1]=id_Song;
        array[2]=content_Lyrics;
        array[3]=flag_Pure_Lyrics;
        return array;
    }

    public Integer getId_Lyrics() {
        return id_Lyrics;
    }

    public void setId_Lyrics(Integer id_Lyrics) {
        this.id_Lyrics = id_Lyrics;
    }

    public Integer getId_Song() {
        return id_Song;
    }

    public void setId_Song(Integer id_Song) {
        this.id_Song = id_Song;
    }

    public String getContent_Lyrics() {
        return content_Lyrics;
    }

    public void setContent_Lyrics(String content_Lyrics) {
        this.content_Lyrics = content_Lyrics;
    }

    public Boolean getFlag_Pure_Lyrics() {
        return flag_Pure_Lyrics;
    }

    public void setFlag_Pure_Lyrics(Boolean flag_Pure_Lyrics) {
        this.flag_Pure_Lyrics = flag_Pure_Lyrics;
    }
}
