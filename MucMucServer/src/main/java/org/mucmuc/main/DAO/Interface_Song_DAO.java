package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;

import java.util.List;

public interface Interface_Song_DAO {

    /**
     * 根据主键查询
     * @param song
     * @return
     */
    Song queryByPK(Song song);

    /**
     * 根据属性(模糊)查询
     * @param song
     * @return
     */
    List<Song> queryByAttribute(Song song);

    /**
     * 查询全部
     * @return
     */
    List<Song> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Song song);

    /**
     * 更新歌曲信息
     * @return
     */
    int update(Song song);

    /**
     * 插入(新增)歌曲
     * @return
     */
    int insertNew(Song song);

    /**
     * 找到此歌曲下的所有标签
     */
    List<Tag> queryTagsbySong(Song song);
}
