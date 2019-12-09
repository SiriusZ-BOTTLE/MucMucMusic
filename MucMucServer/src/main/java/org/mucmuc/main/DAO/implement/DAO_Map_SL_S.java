package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Map_SL_S_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Map_SL_S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class DAO_Map_SL_S implements Interface_Map_SL_S_DAO {

    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public Map_SL_S queryByPK(Map_SL_S map_sl_s) {
        String sql="select * from "+ Set_StringConstants.table_map_sl_s +" where ID_SL = ? AND ID_Song = ? ";
        //查询
        List<Map_SL_S> map_sl_sList=jdbc.query(sql,new Object[]{map_sl_s.getId_SL(),map_sl_s.getId_Song()}, new BeanPropertyRowMapper(Map_SL_S.class));

        if(map_sl_sList==null||map_sl_sList.size()==0)
            return null;

        return map_sl_sList.get(0);
    }

    @Override
    public List<Map_SL_S> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_map_sl_s;

        //查询
        List<Map_SL_S> map_sl_sList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Map_SL_S.class));

        return map_sl_sList;
    }

    @Override
    public int deleteByPK(Map_SL_S map_sl_s) {
        String sql_q="select * from "+ Set_StringConstants.table_map_sl_s+" where ID_SL = ? and ID_Song = ? ";

        //查询
        List<Map_SL_S> map_sl_sList=jdbc.query(sql_q,new Object[]{map_sl_s.getId_SL(),map_sl_s.getId_Song()}, new BeanPropertyRowMapper(Map_SL_S.class));

        if(map_sl_sList==null||map_sl_sList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_map_sl_s+" where ID_SL = "+map_sl_s.getId_SL()+" and ID_Song = "+map_sl_s.getId_Song();
        return jdbc.update(sql_d);
    }

    @Override
    public int deleteBySongListID(Integer id_SongList) {
        String sql="delete from "+ Set_StringConstants.table_map_sl_s+" where ID_SL = ? ";

        return jdbc.update(sql,new Object[]{id_SongList});
    }

    @Override
    public int deleteBySongID(Integer id_Song) {
        String sql="delete from "+ Set_StringConstants.table_map_sl_s+" where ID_Song = ? ";

        return jdbc.update(sql,new Object[]{id_Song});
    }


    @Override
    public int insertNew(Map_SL_S map_sl_s) {
        String sql="insert into "+Set_StringConstants.table_map_sl_s+" values (?,?) ";

        //以下两句效果相同
        return jdbc.update(sql,map_sl_s.getId_SL(),map_sl_s.getId_Song());//这个简洁点
//        return jdbc.update(sql,user.getID_User(),user.getPassword_User(),user.getNickname_User(),user.getIcon_User(),user.getIdiograph_User(),user.getGender_User(),user.getLevel_User(),user.getState_User());

    }
}
