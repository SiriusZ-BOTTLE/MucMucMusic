package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.SongList;

/**
 * @Author ZSR
 *
 *
 */


public interface Interface_SongList_server {


    /**
     * 更新歌单信息
     * @param songList
     * @return
     */
    ResultEntity update(SongList songList);

    /**
     *
     * @param songList
     * @return
     */

    ResultEntity delete(SongList songList);






}
