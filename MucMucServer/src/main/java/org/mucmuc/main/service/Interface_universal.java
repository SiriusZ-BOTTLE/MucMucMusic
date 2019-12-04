package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.*;

@Deprecated
public interface Interface_universal {


    /**
     * 用户创建歌单
     * @param user
     * @param songList
     * @return
     */

    ResultEntity user_create_songList(User user,SongList songList);

    /**
     * 用户发表评论
     * @param user
     * @param com
     * @return
     */
    ResultEntity user_create_comment(User user,Comment com,Song song);







}
