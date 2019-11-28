package org.mucmuc.main.entity;

public class Lyrics {
    private Integer ID_Lyrics;
    private Integer ID_Song;
    private String Content_Lyrics;
    private Boolean Flag_Pure_Lyrics;

    public Integer getID_Lyrics() {
        return ID_Lyrics;
    }

    public void setID_Lyrics(Integer ID_Lyrics) {
        this.ID_Lyrics = ID_Lyrics;
    }

    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }

    public String getContent_Lyrics() {
        return Content_Lyrics;
    }

    public void setContent_Lyrics(String content_Lyrics) {
        Content_Lyrics = content_Lyrics;
    }

    public Boolean getFlag_Pure_Lyrics() {
        return Flag_Pure_Lyrics;
    }

    public void setFlag_Pure_Lyrics(Boolean flag_Pure_Lyrics) {
        Flag_Pure_Lyrics = flag_Pure_Lyrics;
    }
}
