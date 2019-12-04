package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;

import java.util.List;

public interface Interface_SongList_DAO {


    /**
     * 根据主键查询
     * @param sl
     * @return
     */
    SongList queryByPK(SongList sl);

    /**
     * 根据属性(模糊)查询
     * @param sl
     * @return
     */
    List<SongList> queryByAttribute(SongList sl);


    /**
     * 随机查询
     * @param num
     * @return
     */
    List<SongList> queryRandom(Integer num);


    /**
     * 根据ID_User属性(精确)查询
     * @param sl
     * @return
     */
    List<SongList> preciousqueryByID_User(SongList sl);

    /**
     * 查询全部
     * @return
     */
    List<SongList> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(SongList sl);

    /**
     * 更新歌单信息
     * @return
     */
    int update(SongList sl);

    /**
     * 插入(新增)歌单
     * @return
     */
    int insertNew(SongList sl);

    /**
     * 找到歌单下的所有歌曲
     */
    List<Song> queryAllSongBySL(SongList sl);


}
