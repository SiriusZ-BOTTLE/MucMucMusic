package com.example.myapplication.bean_new;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//用户实体
public class User implements Serializable{
//    private static final long serialVersionUID = 3401781428101852077L;

    private String ID_User;//ID
    private String Password_User;//密码
    private String Nickname_User;//昵称
    private String IconFileURL_User;//图标
    private String Idiograph_User;//个性签名
//    private Character Gender_User;//性别
//    private Character Level_User;//级别(分级)
//    private Character State_User;//状态
    //坑点...这里不能使用Character对象...
    //我说怎么老是报错
    private String Gender_User;//性别
    private String Level_User;//级别(分级)
    private String State_User;//状态

    //一共八个属性

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_User);
        list.add(Password_User);
        list.add(Nickname_User);
        list.add(IconFileURL_User);
        list.add(Idiograph_User);
        list.add(Gender_User);
        list.add(Level_User);
        list.add(State_User);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_User!=null)
//            list.add(ID_User);
        if(Password_User!=null)
            list.add(Password_User);
        if(Nickname_User!=null)
            list.add(Nickname_User);
        if(IconFileURL_User !=null)
            list.add(IconFileURL_User);
        if(Idiograph_User!=null)
            list.add(Idiograph_User);
        if(Gender_User!=null)
            list.add(Gender_User);
        if(Level_User!=null)
            list.add(Level_User);
        if(State_User!=null)
            list.add(State_User);

        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[8];
        array[0]=ID_User;
        array[1]=Password_User;
        array[2]=Nickname_User;
        array[3]= IconFileURL_User;
        array[4]=Idiograph_User;
        array[5]=Gender_User;
        array[6]=Level_User;
        array[7]=State_User;
        return array;
    }

    public String getID_User() {
        return ID_User;
    }

    public void setID_User(String ID_User) {
        this.ID_User = ID_User;
    }

    public String getPassword_User() {
        return Password_User;
    }

    public void setPassword_User(String password_User) {
        Password_User = password_User;
    }

    public String getNickname_User() {
        return Nickname_User;
    }

    public void setNickname_User(String nickname_User) {
        Nickname_User = nickname_User;
    }

    public String getIconFileURL_User() {
        return IconFileURL_User;
    }

    public void setIconFileURL_User(String iconFileURL_User) {
        IconFileURL_User = iconFileURL_User;
    }

    public String getIdiograph_User() {
        return Idiograph_User;
    }

    public void setIdiograph_User(String idiograph_User) {
        Idiograph_User = idiograph_User;
    }

    public String getGender_User() {
        return Gender_User;
    }

    public void setGender_User(String gender_User) {
        Gender_User = gender_User;
    }

    public String getLevel_User() {
        return Level_User;
    }

    public void setLevel_User(String level_User) {
        Level_User = level_User;
    }

    public String getState_User() {
        return State_User;
    }

    public void setState_User(String state_User) {
        State_User = state_User;
    }
}
