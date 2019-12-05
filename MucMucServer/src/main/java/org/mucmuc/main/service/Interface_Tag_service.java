package org.mucmuc.main.service;


import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;

/**
 * @author ZSR
 */
public interface Interface_Tag_service {

    /**
     * 随机获取指定数量的记录
     * @return
     */
    ResultEntity getRandom(Integer num);

    /**
     * 获取标签
     * @param tag
     * @return
     */
    ResultEntity get(Tag tag);

    /**
     * 用户创建标签
     * @param song
     * @param tag
     * @return
     */
    ResultEntity create(Song song,Tag tag);

    /**
     * 管理员删除标签
     * @param song
     * @param tag
     * @return
     */
    ResultEntity delete(Song song,Tag tag);


    /**
     * 歌曲的某个标签数+1
     * @param song
     * @param tag
     * @return
     */
    ResultEntity tagCountPlusOne(Song song,Tag tag);


    /**
     * 歌曲的某个标签数-1
     * @param song
     * @param tag
     * @return
     */
    ResultEntity tagCountMinusOne(Song song,Tag tag);





}
