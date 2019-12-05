package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Lyrics_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class DAO_Lyrics implements Interface_Lyrics_DAO {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Lyrics queryByPK(Lyrics lyrics) {

        String sql="select * from "+ Set_StringConstants.table_lyrics +" where ID_lyrics = ? ";
        //查询
        List<Lyrics> lyricsList=jdbc.query(sql,new Object[]{lyrics.getId_Lyrics()}, new BeanPropertyRowMapper(Lyrics.class));

        if(lyricsList==null||lyricsList.size()==0)
            return null;

        return lyricsList.get(0);
    }

    @Override
    public Lyrics queryByID_Song(Lyrics lyrics) {
        String sql="select * from "+ Set_StringConstants.table_lyrics;

        if(lyrics.getId_Song()!=null)
            sql=sql+"where ID_Song = ?";
        else
            return null;

        //查询
        List<Lyrics> lyricsList=jdbc.query(sql,new Object[]{lyrics.getId_Song()}, new BeanPropertyRowMapper(Lyrics.class));

        return lyricsList.get(0);
    }

    @Override
    public List<Lyrics> queryAll() {

        String sql="select * from "+ Set_StringConstants.table_lyrics;

        //查询
        List<Lyrics> lyricsList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Lyrics.class));

        return lyricsList;
    }

    @Override
    public int deleteByPK(Lyrics lyrics) {
        String sql_q="select * from "+ Set_StringConstants.table_lyrics+" where ID_Lyrics = ? ";

        //查询
        List<Lyrics> lyricsList=jdbc.query(sql_q,new Object[]{lyrics.getId_Lyrics()}, new BeanPropertyRowMapper(Lyrics.class));

        if(lyricsList==null||lyricsList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_lyrics+"where ID_Lyrics = "+lyrics.getId_Lyrics();
        return jdbc.update(sql_d);
    }

    @Override
    public int deleteBySong(Lyrics lyrics){

        String sql_q="select * from "+ Set_StringConstants.table_lyrics+" where ID_Song = ? ";

        //查询
        List<Lyrics> lyricsList=jdbc.query(sql_q,new Object[]{lyrics.getId_Song()}, new BeanPropertyRowMapper(Lyrics.class));

        if(lyricsList==null||lyricsList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_lyrics+"where ID_Song = "+lyrics.getId_Song();
        return jdbc.update(sql_d);

    }

    @Override
    public int update(Lyrics lyrics) {
        String sql="update "+Set_StringConstants.table_lyrics+"set ";

        List<Object> list=lyrics.objectList_notNull();//获取非空项

        if(lyrics.getId_Song()!=null)
        {
            sql+="ID_Song = ? ,";
//            list.add(lyrics.getID_Song());
        }
        if(lyrics.getContent_Lyrics()!=null)
        {
            sql+="Content_Lyrics = ? ,";
//            list.add(lyrics.getContent_Lyrics());
        }
        if(lyrics.getFlag_Pure_Lyrics()!=null)
        {
            sql+="Flag_Pure_Lyrics = ? ,";
//            list.add(lyrics.getFlag_Pure_Lyrics());
        }

        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_Lyrics = ? ";
        list.add(lyrics.getId_Lyrics());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新
    }

    @Override
    public int insertNew(Lyrics lyrics) {
        String sql="insert into "+Set_StringConstants.table_lyrics+" values (?,?,?) ";

        //以下两句效果相同
        return jdbc.update(sql,lyrics.getId_Song(),lyrics.getContent_Lyrics(),lyrics.getFlag_Pure_Lyrics());//这个简洁点
//        return jdbc.update(sql,user.getID_User(),user.getPassword_User(),user.getNickname_User(),user.getIcon_User(),user.getIdiograph_User(),user.getGender_User(),user.getLevel_User(),user.getState_User());

    }
}
