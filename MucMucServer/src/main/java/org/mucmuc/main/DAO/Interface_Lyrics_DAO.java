package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Song;

import java.util.List;

public interface Interface_Lyrics_DAO {

    /**
     * 根据主键查询
     * @param lyrics
     * @return
     */
    Lyrics queryByPK(Lyrics lyrics);

    /**
     * 根据属性(模糊)查询
     * @param lyrics
     * @return
     */
    Lyrics queryByID_Song(Lyrics lyrics);

    /**
     * 查询全部
     * @return
     */
    List<Lyrics> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Lyrics lyrics);

    /**
     * 根据歌曲id删除
     * @return
     */
    int deleteBySong(Lyrics lyrics);

    /**
     * 更新歌词信息
     * @return
     */
    int update(Lyrics lyrics);

    /**
     * 插入(新增)歌曲
     * @return
     */
    int insertNew(Lyrics lyrics);
}
