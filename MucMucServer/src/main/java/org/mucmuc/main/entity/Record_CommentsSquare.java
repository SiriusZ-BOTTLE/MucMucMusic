package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Record_CommentsSquare {
    private Integer id_Comment;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Comment);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(id_Comment!=null)
            list.add(id_Comment);

        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[1];
        array[0]=id_Comment;
        return array;
    }

    public Integer getId_Comment() {
        return id_Comment;
    }

    public void setId_Comment(Integer id_Comment) {
        this.id_Comment = id_Comment;
    }
}
