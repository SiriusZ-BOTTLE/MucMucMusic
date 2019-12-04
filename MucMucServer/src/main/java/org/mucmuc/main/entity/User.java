package org.mucmuc.main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//用户实体
public class User implements Serializable{
//    private static final long serialVersionUID = 3401781428101852077L;

    private String id_User;//ID
    private String password_User;//密码
    private String nickname_User;//昵称
    private String iconFile_User;//图标
    private String idiograph_User;//个性签名
//    private Character Gender_User;//性别
//    private Character Level_User;//级别(分级)
//    private Character State_User;//状态
    //坑点...这里不能使用Character对象...
    //我说怎么老是报错
    private String gender_User;//性别
    private String level_User;//级别(分级)
    private String state_User;//状态

    //一共八个属性

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_User);
        list.add(password_User);
        list.add(nickname_User);
        list.add(iconFile_User);
        list.add(idiograph_User);
        list.add(gender_User);
        list.add(level_User);
        list.add(state_User);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
//    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

        if(id_User!=null)
            list.add(id_User);
        if(password_User!=null)
            list.add(password_User);
        if(nickname_User!=null)
            list.add(nickname_User);
        if(iconFile_User !=null)
            list.add(iconFile_User);
        if(idiograph_User!=null)
            list.add(idiograph_User);
        if(gender_User!=null)
            list.add(gender_User);
        if(level_User!=null)
            list.add(level_User);
        if(state_User!=null)
            list.add(state_User);

        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[8];
        array[0]=id_User;
        array[1]=password_User;
        array[2]=nickname_User;
        array[3]= iconFile_User;
        array[4]=idiograph_User;
        array[5]=gender_User;
        array[6]=level_User;
        array[7]=state_User;
        return array;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getPassword_User() {
        return password_User;
    }

    public void setPassword_User(String password_User) {
        this.password_User = password_User;
    }

    public String getNickname_User() {
        return nickname_User;
    }

    public void setNickname_User(String nickname_User) {
        this.nickname_User = nickname_User;
    }

    public String getIconFile_User() {
        return iconFile_User;
    }

    public void setIconFile_User(String iconFile_User) {
        this.iconFile_User = iconFile_User;
    }

    public String getIdiograph_User() {
        return idiograph_User;
    }

    public void setIdiograph_User(String idiograph_User) {
        this.idiograph_User = idiograph_User;
    }

    public String getGender_User() {
        return gender_User;
    }

    public void setGender_User(String gender_User) {
        this.gender_User = gender_User;
    }

    public String getLevel_User() {
        return level_User;
    }

    public void setLevel_User(String level_User) {
        this.level_User = level_User;
    }

    public String getState_User() {
        return state_User;
    }

    public void setState_User(String state_User) {
        this.state_User = state_User;
    }
}
