package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.*;

import java.util.List;

public interface Interface_Map_S_T_DAO {

    /**
     * 根据主键查询
     * @param map_s_t
     * @return
     */
    Map_S_T queryByPK(Map_S_T map_s_t);

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
    List<Map_S_T> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Map_S_T map_s_t);


    /**
     * 更新映射
     * @return
     */
    int update(Map_S_T map_s_t);

    /**
     * 插入(新增)映射
     * @return
     */
    int insertNew(Map_S_T map_s_t);


    /**
     * 根据标签ID删除所有映射
     * @return
     */
    int deleteByTagID(Integer id_Tag);

    /**
     * 根据歌曲ID删除所有映射
     * @param id_Song
     * @return
     */
    int deleteBySongID(Integer id_Song);


}
