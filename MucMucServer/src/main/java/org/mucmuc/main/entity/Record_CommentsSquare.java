package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Record_CommentsSquare {
    private Integer ID_Comment;

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Comment);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(ID_Comment!=null)
            list.add(ID_Comment);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[1];
        array[0]=ID_Comment;
        return array;
    }

    public Integer getID_Comment() {
        return ID_Comment;
    }

    public void setID_Comment(Integer ID_Comment) {
        this.ID_Comment = ID_Comment;
    }
}
