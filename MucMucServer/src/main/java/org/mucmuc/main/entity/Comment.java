package org.mucmuc.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//评论实体
public class Comment {
    private Integer id_Comment;//评论ID
    private Integer id_Song;//歌曲ID
    private Integer id_User;//用户ID
    private Integer id_ReplyComment;//回复的评论ID
    private String content_Comment;//内容
    private Date releaseTime_Comment;//发布时间ID_Reply
    private Integer likes_Comment;//点赞数
    private Integer dislikes_Comment;//踩数
    private Integer score_Comment;//打分

    //转换为Object列表,方便DAO层实现的同时也方便打印
    public List<Object> objectList()
    {
        List<Object> list=new ArrayList<Object>();
        list.add(id_Comment);
        list.add(id_Song);
        list.add(id_User);
        list.add(id_ReplyComment);
        list.add(content_Comment);
        list.add(releaseTime_Comment);
        list.add(likes_Comment);
        list.add(dislikes_Comment);
        list.add(score_Comment);
        return list;
    }

    //转换为object列表,不包含空值(该函数功能同上)
    @Deprecated
    public List<Object> objectList_notNull()
    {
        List<Object> list=new ArrayList<Object>();

//        if(ID_Comment!=null)
//            list.add(ID_Comment);
        if(id_Song!=null)
            list.add(id_Song);
        if(id_User!=null)
            list.add(id_User);
        if(id_ReplyComment!=null)
            list.add(id_ReplyComment);
        if(content_Comment!=null)
            list.add(content_Comment);
        if(releaseTime_Comment!=null)
            list.add(releaseTime_Comment);
        if(likes_Comment!=null)
            list.add(likes_Comment);
        if(dislikes_Comment!=null)
            list.add(dislikes_Comment);
        if(score_Comment!=null)
            list.add(score_Comment);


        return list;
    }

    //转换为Object数组, 方便insertNew
    public Object[] objectArray()
    {
        Object[] array=new Object[9];
        array[0]=id_Comment;
        array[1]=id_Song;
        array[2]=id_User;
        array[3]=id_ReplyComment;
        array[4]=content_Comment;
        array[5]=releaseTime_Comment;
        array[6]=likes_Comment;
        array[7]=dislikes_Comment;
        array[8]=score_Comment;
        return array;
    }


    public Integer getId_Comment() {
        return id_Comment;
    }

    public void setId_Comment(Integer id_Comment) {
        this.id_Comment = id_Comment;
    }

    public Integer getId_Song() {
        return id_Song;
    }

    public void setId_Song(Integer id_Song) {
        this.id_Song = id_Song;
    }

    public Integer getId_User() {
        return id_User;
    }

    public void setId_User(Integer id_User) {
        this.id_User = id_User;
    }

    public Integer getId_ReplyComment() {
        return id_ReplyComment;
    }

    public void setId_ReplyComment(Integer id_ReplyComment) {
        this.id_ReplyComment = id_ReplyComment;
    }

    public String getContent_Comment() {
        return content_Comment;
    }

    public void setContent_Comment(String content_Comment) {
        this.content_Comment = content_Comment;
    }

    public Date getReleaseTime_Comment() {
        return releaseTime_Comment;
    }

    public void setReleaseTime_Comment(Date releaseTime_Comment) {
        this.releaseTime_Comment = releaseTime_Comment;
    }

    public Integer getLikes_Comment() {
        return likes_Comment;
    }

    public void setLikes_Comment(Integer likes_Comment) {
        this.likes_Comment = likes_Comment;
    }

    public Integer getDislikes_Comment() {
        return dislikes_Comment;
    }

    public void setDislikes_Comment(Integer dislikes_Comment) {
        this.dislikes_Comment = dislikes_Comment;
    }

    public Integer getScore_Comment() {
        return score_Comment;
    }

    public void setScore_Comment(Integer score_Comment) {
        this.score_Comment = score_Comment;
    }
}
