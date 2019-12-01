package org.mucmuc.main.service.implement;

//交互实体
import org.mucmuc.main.entity.InteractionEntity.*;
//要实现的接口
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.Interface_User_service;
//调用DAO层的接口
import org.mucmuc.main.DAO.Interface_User_DAO;
//自动配置包
import org.springframework.beans.factory.annotation.Autowired;
//服务包
import org.springframework.stereotype.Service;


@Service(value = "Service_User")
public class Service_User implements Interface_User_service {


    @Autowired
    private Interface_User_DAO userDAO;

    @Override
    public ResultEntity login(User user) {

        ResultEntity resultEntity=new ResultEntity();

        if(user.getID_User()==null)
        {
            resultEntity.setInfo_error("<ERROR> ID_User is NULL");
            return resultEntity;
        }

        if(user.getPassword_User()==null)
        {
            resultEntity.setInfo_error("<ERROR> Password_User is NULL");
            return resultEntity;
        }

        User user_db=userDAO.queryByPK(user);//调用持久层接口
        if(user_db==null)
        {
            resultEntity.setInfo_operation("用户不存在");
            return resultEntity;
        }
        if(user_db.getState_User().equals('B'))
        {
            resultEntity.setInfo_operation("用户已被封禁");
            return resultEntity;
        }
        if(!user.getPassword_User().equals(user_db.getPassword_User()))
        {
            resultEntity.setInfo_operation("密码错误");
            return resultEntity;
        }
        resultEntity.setObject(user_db);//将数据库中的用户信息返回
        resultEntity.setState(true);//没有遇到问题,操作成功
        return resultEntity;
    }

    @Override
    public ResultEntity register(User user) {
        ResultEntity resultEntity=new ResultEntity();

        User user_db=userDAO.queryByPK(user);

        if(user_db!=null)
        {
            resultEntity.setInfo_operation("用户ID已被使用");
            return resultEntity;
        }

        //完整性检查
        String pwd=user.getPassword_User();
        if(pwd==null||pwd.length()<8)
        {
            resultEntity.setInfo_error("<ERROR> Password_User illegal");
            return resultEntity;
        }

        String c=user.getGender_User();
        if(c==null||(c.equals("M")&&c.equals("F")))
        {
            System.out.println(c);
            resultEntity.setInfo_error("<ERROR> Gender_User illegal");
            return resultEntity;
        }
        user.setLevel_User("0");//普通用户
        user.setState_User("N");//状态正常
        userDAO.insertNew(user);//调用持久层接口
        resultEntity.setObject(user);
        resultEntity.setState(true);//没有遇到问题,操作成功
        return resultEntity;
    }

    @Override
    public ResultEntity get(User user) {
        return null;
    }

    @Override
    public ResultEntity getAll() {
        return null;
    }

    @Override
    public ResultEntity update(User user) {
        return null;
    }

    @Override
    public ResultEntity delete(User user) {
        return null;
    }
}
