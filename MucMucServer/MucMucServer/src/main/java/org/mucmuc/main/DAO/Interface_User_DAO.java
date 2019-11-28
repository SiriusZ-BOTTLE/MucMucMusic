package org.mucmuc.main.DAO;

import org.mucmuc.main.entity.User;
import java.util.List;

public interface Interface_User_DAO {

    /**
     * 根据主键查询
     * @param user
     * @return
     */
    User queryByPK(User user);

    /**
     * 根据属性(模糊)查询
     * @param user
     * @return
     */
    List<User> queryByAttribute(User user);

    /**
     * 查询全部
     * @return
     */
    List<User> queryAll();

    /**
     * 根据主键删除
     * @return
     */
    int deleteByPK(User user);

    /**
     * 更新用户信息
     * @return
     */
    int update(User user);

    /**
     * 插入(新增)用户
     * @return
     */
    int insertNew(User user);



}
