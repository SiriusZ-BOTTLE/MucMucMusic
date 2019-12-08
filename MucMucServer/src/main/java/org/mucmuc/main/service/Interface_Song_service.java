package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;

/**
 * @Author: Mjj
 * @Date: 2019/12/02
 * @Desprition: 用户业务接口
 */

public interface Interface_Song_service {

    /**
     * 随机获取指定数量的记录
     * @return
     */
    ResultEntity getRandom(Integer num);


    /**
     * 播放歌曲(song)
     * @param song
     * @return
     */
    @Deprecated
    ResultEntity play(Song song);

    /**
     * 搜索歌曲(按名称)
     * @param song
     * @return
     */
    ResultEntity search(Song song);


    /**
     * 新增歌曲（管理员）(song)
     * @param song
     * @return
     */
    ResultEntity add(Song song);

    /**
     * 删除歌曲(管理员)(song)
     * @param song
     * @return
     */
    ResultEntity delete(Song song);

    /**
     * 更新歌曲信息（管理员）(song)
     * @param song
     * @return
     */
    ResultEntity update(Song song);

    /**
     * 查询(song)
     * @param song
     * @return
     */
    ResultEntity get(Song song);

    /**
     * 获取歌单中的歌曲
     * @return
     */
    ResultEntity getSongInSongList(SongList songList);



}
