package org.mucmuc.main.entity;

import java.util.Date;

public class SongList {
    private Integer ID_SL;
    private String ID_User;
    private String Name_SL;
    private Date Date_SL;
    private String Description_SL;

    public Integer getID_SL() {
        return ID_SL;
    }

    public void setID_SL(Integer ID_SL) {
        this.ID_SL = ID_SL;
    }

    public String getID_User() {
        return ID_User;
    }

    public void setID_User(String ID_User) {
        this.ID_User = ID_User;
    }

    public String getName_SL() {
        return Name_SL;
    }

    public void setName_SL(String name_SL) {
        Name_SL = name_SL;
    }

    public Date getDate_SL() {
        return Date_SL;
    }

    public void setDate_SL(Date date_SL) {
        Date_SL = date_SL;
    }

    public String getDescription_SL() {
        return Description_SL;
    }

    public void setDescription_SL(String description_SL) {
        Description_SL = description_SL;
    }
}
