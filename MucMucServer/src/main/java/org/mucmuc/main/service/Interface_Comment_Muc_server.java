package org.mucmuc.main.service;

import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.User;

public interface Interface_Comment_Muc_server {

    /**
     * 写评论，以及更新歌曲score(oldcomment user song)
     * @param requestEntity
     * @return
     */
    ResultEntity write(RequestEntity requestEntity);

    /**
     * 修改自己评论内容，以及更新歌曲score
     */
    ResultEntity modifyContent(RequestEntity requestEntity);

    /**
     * 点赞
     * @param requestEntity
     * @return
     */
    ResultEntity likes(RequestEntity requestEntity);

    /**
     * 踩
     * @param requestEntity
     * @return
     */
    ResultEntity dislikes(RequestEntity requestEntity);


    /**
     * 删除评论(管理员),更新歌曲score
     * @param requestEntity
     * @return
     */
    ResultEntity delete(RequestEntity requestEntity);

    /**
     * 显示当前评论的子评论（Comment）
     * @param requestEntity
     * @return
     */
    ResultEntity queryByReply(RequestEntity requestEntity);

    /**
     * 显示当前歌曲的评论(song)
     * @param requestEntity
     * @return
     */
    ResultEntity queryBySong(RequestEntity requestEntity);

    /**
     * 获得最新评论(song)
     * @param requestEntity
     * @return
     */
    ResultEntity queryNew(RequestEntity requestEntity);

}
