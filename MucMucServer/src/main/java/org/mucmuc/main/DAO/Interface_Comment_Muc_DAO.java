package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.User;

import java.util.List;

public interface Interface_Comment_Muc_DAO {

    /**
     * 根据主键查询
     * @param comment
     * @return
     */
    Comment queryByPK(Comment comment);


    /**
     * 根据属性(模糊)查询，按时间顺序降序排列     (筛选机制可以改进，保留以前最优质评论，更新现在的优秀评论)
     * @param comment1 comment2
     * @return
     */
    List<Comment> queryOrderbyTime(Comment comment1,Comment comment2);

    /**
     * 根据属性(模糊)查询，按Likes顺序降序排列
     * @param comment1 comment2
     * @return
     */
    List<Comment> queryOrderbyLikes(Comment comment1,Comment comment2);

    /**
     * 查询全部
     * @return
     */
    List<Comment> queryAll();

    /**
     * 根据主键删除(前端设置只能删除自己的)
     * @return
     */
    int deleteByPK(Comment comment);

    /**
     * 根据属性删除
     */
    int deleteByAttribute(Comment comment1,Comment comment2);

    /**
     * 更新评论内容(前端设置只能更新自己的)
     * @return
     */
    int update(Comment comment);

    /**
     * 插入(新增)评论
     * @return
     */
    int insertNew(Comment comment);


}
