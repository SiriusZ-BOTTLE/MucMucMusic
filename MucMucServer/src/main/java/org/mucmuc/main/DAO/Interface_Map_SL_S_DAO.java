package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Map_SL_S;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;

import java.util.List;

public interface Interface_Map_SL_S_DAO {

    /**
     * 根据主键查询
     * @param map_sl_s
     * @return
     */
    Map_SL_S queryByPK(Map_SL_S map_sl_s);

//    /**
//     * 根据属性(模糊)查询
//     * @param lyrics
//     * @return
//     */
//    Lyrics queryByID_Song(Lyrics lyrics);

    /**
     * 查询全部
     * @return
     */
    List<Map_SL_S> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Map_SL_S map_sl_s);


    /**
     * 根据歌单的ID删除所有映射
     * @return
     */
    int deleteBySongListID(Integer id_SongList);

    /**
     * 根据歌曲ID删除所有映射
     * @param id_Song
     * @return
     */
    int deleteBySongID(Integer id_Song);



    /**
     * 插入(新增)映射
     * @return
     */
    int insertNew(Map_SL_S map_sl_s);


}
