package org.mucmuc.main.entity;

import java.util.Date;

public class Song {
    private Integer ID_Song;
    private String Name_Song;
    private String Singer_Song;
    private Date ReleaseDate_Song;
    private String FileURL_Song;

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
