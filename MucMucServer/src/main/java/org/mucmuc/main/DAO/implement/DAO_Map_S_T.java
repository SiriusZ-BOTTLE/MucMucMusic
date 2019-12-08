package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Map_S_T_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Map_S_T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class DAO_Map_S_T implements Interface_Map_S_T_DAO {

    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public Map_S_T queryByPK(Map_S_T map_s_t) {
        String sql="select * from "+ Set_StringConstants.table_map_s_t+" where id_Song =? and id_Tag = ?";

        //查询
        List<Map_S_T> list=jdbc.query(sql,new Object[]{map_s_t.getId_Song(),map_s_t.getId_Tag()}, new BeanPropertyRowMapper(Map_S_T.class));
        if(list==null||list.size()==0)
            return null;

        return list.get(0);
    }

    @Override
    public List<Map_S_T> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_map_s_t;

        //查询
        List<Map_S_T> map_s_tList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Map_S_T.class));

        return map_s_tList;
    }

    @Override
    public int deleteByPK(Map_S_T map_s_t) {
        String sql_q="select * from "+ Set_StringConstants.table_map_s_t+" where ID_Song = ? and ID_Tag = ? ";

        //查询
        List<Map_S_T> map_s_tList=jdbc.query(sql_q,new Object[]{map_s_t.getId_Song(),map_s_t.getId_Tag()}, new BeanPropertyRowMapper(Map_S_T.class));

        if(map_s_tList==null||map_s_tList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_map_s_t+" where ID_Song = "+map_s_t.getId_Song()+" and ID_Tag = "+map_s_t.getId_Tag();
        return jdbc.update(sql_d);
    }

    @Override
    public int update(Map_S_T map_s_t) {
        String sql="update "+Set_StringConstants.table_map_s_t+"  set ";

        List<Object> map_s_tList=map_s_t.objectList_notNull();//获取非空项

        if(map_s_t.getNum()!=null)
        {
            sql+="Num = ? ,";
//            list.add(lyrics.getID_Song());
        }


        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_Song = ? and ID_Tag = ?";
        map_s_tList.add(map_s_t.getId_Song());
        map_s_tList.add(map_s_t.getId_Tag());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,map_s_tList.toArray());//执行更新
    }

    @Override
    public int insertNew(Map_S_T map_s_t) {
        String sql="insert into "+Set_StringConstants.table_map_s_t+" values (?,?,?) ";

        return jdbc.update(sql,map_s_t.getId_Song(),map_s_t.getId_Tag(),map_s_t.getNum());
//        return jdbc.update(sql,user.getID_User(),user.getPassword_User(),user.getNickname_User(),user.getIcon_User(),user.getIdiograph_User(),user.getGender_User(),user.getLevel_User(),user.getState_User());

    }

    @Override
    public int deleteByTagID(Integer id_Tag) {
        String sql="delete from "+Set_StringConstants.table_map_s_t+" where id_Tag = ? ";
        return jdbc.update(sql,new Object[]{id_Tag});
    }

    @Override
    public int deleteBySongID(Integer id_Song) {
        String sql="delete from "+Set_StringConstants.table_map_s_t+" where id_Tag = ? ";
        return jdbc.update(sql,new Object[]{id_Song});
    }
}
