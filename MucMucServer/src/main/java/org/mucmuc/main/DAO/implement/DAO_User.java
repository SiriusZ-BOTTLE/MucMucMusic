package org.mucmuc.main.DAO.implement;

//自动装配注解
import org.springframework.beans.factory.annotation.Autowired;
//接口
import org.mucmuc.main.DAO.Interface_User_DAO;
//字符串常量包
import org.mucmuc.main.DAO.Set_StringConstants;
//实体
import org.mucmuc.main.entity.User;
//JDBC
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DAO_User implements Interface_User_DAO {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public User queryByPK(User user) {

        String sql="select * from "+ Set_StringConstants.table_user +" where ID_User = ? ";
        //查询
        List<User> userList=jdbc.query(sql,new Object[]{user.getID_User()}, new BeanPropertyRowMapper(User.class));

        if(userList==null||userList.size()==0)
            return null;

        return userList.get(0);
    }

    @Override
    public List<User> queryByAttribute(User user) {

        String sql="select * from "+ Set_StringConstants.table_user;

        if(user.getNickname_User()!=null)
            sql=sql+"where Nickname_User like ?";
        else
            return null;

        //查询
        List<User> userList=jdbc.query(sql,new Object[]{user.getNickname_User()}, new BeanPropertyRowMapper(User.class));

        return userList;
    }

    @Override
    public List<User> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_user;

        //查询
        List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));

        return userList;
    }

    @Override
    public int deleteByPK(User user) {

        String sql_q="select * from "+ Set_StringConstants.table_user+" where ID_User = ? ";

        //查询
        List<User> userList=jdbc.query(sql_q,new Object[]{user.getID_User()}, new BeanPropertyRowMapper(User.class));

        if(userList==null||userList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_user+"where ID_User = "+user.getID_User();
        return jdbc.update(sql_d);
    }

    @Override
    public int update(User user) {

        String sql="update "+Set_StringConstants.table_user+"set ";

        List<Object> list=user.objectList();//获取非空项

        if(user.getPassword_User()!=null)
        {
            sql+="Password_User= ? ,";
//            list.add(user.getPassword_User());
        }
        if(user.getNickname_User()!=null)
        {
            sql+="Nickname_User= ? ,";
//            list.add(user.getNickname_User());
        }
        if(user.getIconFileURL_User()!=null)
        {
            sql+="Icon_User= ? ,";
//            list.add(user.getIcon_User());
        }
        if(user.getIdiograph_User()!=null)
        {
            sql+="Idiograph_User= ? ,";
//            list.add(user.getIdiograph_User());
        }
        if(user.getGender_User()!=null)
        {
            sql+="Gender_User= ? ,";
//            list.add(user.getGender_User());
        }
        if(user.getLevel_User()!=null)
        {
            sql+="Level_User= ? ,";
//            list.add(user.getLevel_User());
        }
        if(user.getState_User()!=null)
        {
            sql+="State_User= ? ,";
//            list.add(user.getState_User());
        }

        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_User = ? ";
        list.add(user.getID_User());
            //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新

    }

    @Override
    public int insertNew(User user) {

        String sql="insert into "+Set_StringConstants.table_user+" values (?,?,?,?,?,?,?,?) ";

        //以下两句效果相同
        return jdbc.update(sql,user.objectArray());//这个简洁点
//        return jdbc.update(sql,user.getID_User(),user.getPassword_User(),user.getNickname_User(),user.getIcon_User(),user.getIdiograph_User(),user.getGender_User(),user.getLevel_User(),user.getState_User());
    }


//    public static void main(String args[])
//    {
//        DAO_User userDAO=new DAO_User();
//        User u=new User();
//        List<User> list=userDAO.queryAll();
//
//        System.out.println(list);
//
//    }

}
