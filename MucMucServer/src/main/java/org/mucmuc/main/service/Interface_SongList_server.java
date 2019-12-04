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
     * 获取歌单(信息)
     * @param songList
     * @return
     */

    ResultEntity get(SongList songList);


    /**
     * 根据属性模糊查询(只支持歌单名称模糊查询)
     * @return
     */
    ResultEntity getByAttribute(SongList songList);


    /**
     * 更新歌单信息
     * @param songList
     * @return
     */
    ResultEntity update(SongList songList);

    /**
     * 删除歌单
     * @param songList
     * @return
     */

    ResultEntity delete(SongList songList);






}
