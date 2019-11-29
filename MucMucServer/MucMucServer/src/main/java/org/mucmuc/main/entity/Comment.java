package org.mucmuc.main.entity;

import java.util.Date;

//评论实体
public class Comment {
    private Integer ID_Comment;//评论ID
    private Integer ID_Song;//歌曲ID
    private Integer ID_User;//用户ID
    private Integer ID_ReplyComment;//回复的评论ID
    private String Content_Comment;//内容
    private Date ReleaseTime_Comment;//发布时间ID_Reply
    private Integer Likes_Comment;//点赞数
    private Integer Dislikes_Comment;//踩数
    private Integer Score_Comment;//打分

    public Integer getID_Comment() {
        return ID_Comment;
    }

    public void setID_Comment(Integer ID_Comment) {
        this.ID_Comment = ID_Comment;
    }

    public Integer getID_Song() {
        return ID_Song;
    }

    public void setID_Song(Integer ID_Song) {
        this.ID_Song = ID_Song;
    }

    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
    }

    public Integer getID_ReplyComment() {
        return ID_ReplyComment;
    }

    public void setID_ReplyComment(Integer ID_ReplyComment) {
        this.ID_ReplyComment = ID_ReplyComment;
    }

    public String getContent_Comment() {
        return Content_Comment;
    }

    public void setContent_Comment(String content_Comment) {
        Content_Comment = content_Comment;
    }

    public Date getReleaseTime_Comment() {
        return ReleaseTime_Comment;
    }

    public void setReleaseTime_Comment(Date releaseTime_Comment) {
        ReleaseTime_Comment = releaseTime_Comment;
    }

    public Integer getLikes_Comment() {
        return Likes_Comment;
    }

    public void setLikes_Comment(Integer likes_Comment) {
        Likes_Comment = likes_Comment;
    }

    public Integer getDislikes_Comment() {
        return Dislikes_Comment;
    }

    public void setDislikes_Comment(Integer dislikes_Comment) {
        Dislikes_Comment = dislikes_Comment;
    }

    public Integer getScore_Comment() {
        return Score_Comment;
    }

    public void setScore_Comment(Integer score_Comment) {
        Score_Comment = score_Comment;
    }
}
