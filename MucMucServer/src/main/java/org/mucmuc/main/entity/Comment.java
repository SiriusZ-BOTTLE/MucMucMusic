package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(ID_Comment);
        list.add(ID_Song);
        list.add(ID_User);
        list.add(ID_ReplyComment);
        list.add(Content_Comment);
        list.add(ReleaseTime_Comment);
        list.add(Likes_Comment);
        list.add(Dislikes_Comment);
        list.add(Score_Comment);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Comment!=null)
//            list.add(ID_Comment);
        if(ID_Song!=null)
            list.add(ID_Song);
        if(ID_User!=null)
            list.add(ID_User);
        if(ID_ReplyComment!=null)
            list.add(ID_ReplyComment);
        if(Content_Comment!=null)
            list.add(Content_Comment);
        if(ReleaseTime_Comment!=null)
            list.add(ReleaseTime_Comment);
        if(Likes_Comment!=null)
            list.add(Likes_Comment);
        if(Dislikes_Comment!=null)
            list.add(Dislikes_Comment);
        if(Score_Comment!=null)
            list.add(Score_Comment);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[9];
        array[0]=ID_Comment;
        array[1]=ID_Song;
        array[2]=ID_User;
        array[3]=ID_ReplyComment;
        array[4]=Content_Comment;
        array[5]=ReleaseTime_Comment;
        array[6]=Likes_Comment;
        array[7]=Dislikes_Comment;
        array[8]=Score_Comment;
        return array;
    }


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
