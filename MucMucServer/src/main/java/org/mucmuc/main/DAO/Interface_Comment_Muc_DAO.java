package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Comment;

import java.util.List;

public interface Interface_Comment_Muc_DAO {

    /**
     * 根据主键查询
     * @param comment
     * @return
     */
    Comment queryByPK(Comment comment);

    /**
     * 根据属性(模糊)查询
     * @param comment1 comment2
     * @return
     */
    List<Comment> queryByAttribute(Comment comment1,Comment comment2);

    /**
     * 查询全部
     * @return
     */
    List<Comment> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(Comment comment);

    /**
     * 根据属性删除
     */
    int deleteByAttribute(Comment comment1,Comment comment2);

    /**
     * 更新评论信息
     * @return
     */
    int update(Comment comment);

    /**
     * 插入(新增)评论
     * @return
     */
    int insertNew(Comment comment);

}
