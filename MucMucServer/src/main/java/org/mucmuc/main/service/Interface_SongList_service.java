package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.entity.User;

/**
 * @author ZSR
 *
 *
 */


public interface Interface_SongList_service {


    /**
     * 随机获取指定数量的记录
     * @return
     */
    ResultEntity getRandom(Integer num);


    /**
     * 获取歌单(信息)
     * @param songList
     * @return
     */

    ResultEntity get(SongList songList);


    /**
     * 根据属性模糊查找(只支持歌单名称模糊查询)
     * @return
     */
    ResultEntity search(SongList songList);


    /**
     * 获取用户的所有歌单
     * @param user
     * @return
     */
    ResultEntity getUserSongList(User user);



    /**
     * 添加歌曲至歌单
     * @return
     */
    ResultEntity addSongToSongList(Song song, SongList songList);

    /**
     * 从歌单中移除歌曲
     * @param song
     * @param songList
     * @return
     */
    ResultEntity removeSongFromSongList(Song song, SongList songList);


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
