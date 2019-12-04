package org.mucmuc.main.service;

import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.User;

public interface Interface_Comment_server {


    ResultEntity getRandom()





    /**
     * 写评论，以及更新歌曲score(comment song user)  //comment包括发布时间，父评论id
     * @param comment
     * @return
     */
    ResultEntity write(Comment comment, Song song, User user);

    /**
     * 修改自己评论内容，以及更新歌曲score
     */
    ResultEntity modifyContent(Comment comment);

    /**
     * 点赞
     * @param comment
     * @return
     */
    ResultEntity likes(Comment comment);

    /**
     * 踩
     * @param comment
     * @return
     */
    ResultEntity dislikes(Comment comment);

    /**
     * 修改评论Score
     * @param comment
     * @return
     */
    ResultEntity modifyscore(Comment comment);

    /**
     * 删除评论(管理员),更新歌曲score
     * @param comment
     * @return
     */
    ResultEntity delete(Comment comment);

    /**
     * 显示当前评论的子评论（Comment）
     * @param comment
     * @return
     */
    ResultEntity queryByReply(Comment comment);

    /**
     * 显示当前歌曲的评论(song)
     * @param comment
     * @return
     */
    ResultEntity queryBySong(Comment comment);

    /**
     * 获得最新评论(song)
     * @return
     */
    ResultEntity queryNew();

}
