package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;

/**
 * @Author: Mjj
 * @Date: 2019/12/02
 * @Desprition: 用户业务接口
 */

public interface Interface_Song_server {

    /**
     * 播放歌曲
     * @param song
     * @return
     */
    ResultEntity play(Song song);

    /**
     * 新增歌曲（管理员）
     * @param song
     * @return
     */
    ResultEntity add(Song song);

    /**
     * 删除歌曲(管理员)
     * @param song
     * @return
     */
    ResultEntity delete(Song song);

    /**
     * 更新歌曲信息（管理员）
     * @param song
     * @return
     */
    ResultEntity updateInfo(Song song);

    /**
     * 查询
     * @param song
     * @return
     */
    ResultEntity queryBySong(Song song);



//    /**
//     * 获得歌曲所在歌单
//     * @param song
//     * @return
//     */
//    ResultEntity querySLBySong(Song song);

    /**
     * 获得歌曲的所有标签
     * @param song
     * @return
     */
    ResultEntity queryTagBySong(Song song);

}
