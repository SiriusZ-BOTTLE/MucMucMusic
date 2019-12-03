package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.User;


/**
 * @Author: ZSR
 * @Date: 2019/11/26
 * @Desprition: 用户业务接口
 */

public interface Interface_User_service {


    /**
     * 登录
     * @param user
     * @return
     */
    ResultEntity login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    ResultEntity register(User user);

    /**
     * 多条件获取用户
     * @param user
     * @return
     */
    ResultEntity get(User user);

    /**
     * 获取全部用户
     * @param
     * @return
     */
    ResultEntity getAll();

    /**
     * 更新用户
     * @param user
     * @return
     */
    ResultEntity update(User user);


    /**
     * 删除用户
     * @param user0
     * @param user1
     * @return
     */
    ResultEntity delete(User user0,User user1);


}
