package org.mucmuc.main.DAO;



import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;

import java.util.List;

public interface Interface_Tag_DAO {

    /**
     * 根据主键查询
     * @param tag
     * @return
     */
    Tag queryByPK(Tag tag);

    /**
     * 根据标签名查询
     * @param tag
     * @return
     */
    Tag queryByName(Tag tag);

    /**
     * 随机查询
     * @param num
     * @return
     */
    List<Tag> queryRandom(Integer num);

    /**
     * 根据属性(模糊)查询
     * @param tag
     * @return
     */
    List<Tag> queryByAttribute(Tag tag);

    /**
     * 查询全部
     * @return
     */
    List<Tag> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Tag tag);

    /**
     * 更新标签信息
     * @return
     */
    int update(Tag tag);

    /**
     * 插入(新增)标签
     * @return
     */
    int insertNew(Tag tag);

    /**
     * 找到此标签下的所有歌曲
     */
    List<Song> querySongsByTag(Tag tag);

}
