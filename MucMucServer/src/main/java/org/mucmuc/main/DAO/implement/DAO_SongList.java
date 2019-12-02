package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_SongList_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.SL_S;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication              //下面songList都为歌单对象
public class DAO_SongList implements Interface_SongList_DAO {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public SongList queryByPK(SongList sl) {
        String sql="select * from "+ Set_StringConstants.table_songlist +" where ID_SL = ? ";
        //查询
        List<SongList> slList=jdbc.query(sql,new Object[]{sl.getId_SL()}, new BeanPropertyRowMapper(SongList.class));

        if(slList==null||slList.size()==0)
            return null;

        return slList.get(0);
    }

    @Override
    public List<SongList> queryByAttribute(SongList sl) {

        String sql="select * from "+ Set_StringConstants.table_songlist+" where ";

        List<Object> list = sl.objectList();//获取非空项

        if(sl.getId_User()!=null)
            sql=sql+" ID_User like ? and";
        if(sl.getName_SL()!=null)
            sql=sql+" Name_SL like ? and";
        if(sl.getDate_SL()!=null)
            sql=sql+" Date_SL = ? and";
        if(sl.getDescription_SL()!=null)
            sql=sql+" Description_SL like ? and";

        if(sql.endsWith("where "))
        {
            return null;
        }

        if(sql.endsWith("and"))
        {
            sql=sql.substring(0,sql.length()-3);//缩减
        }

        //查询
        List<SongList> slList=jdbc.query(sql,new Object[]{list.toArray()}, new BeanPropertyRowMapper(SongList.class));

        return slList;
    }

    @Override
    public List<SongList> preciousqueryByID_User(SongList sl){

        String sql="select * from "+ Set_StringConstants.table_songlist+" where ";

        List<Object> list = sl.objectList();//获取非空项

        if(sl.getId_User()!=null)
            sql=sql+" ID_User = ? and";
        if(sl.getName_SL()!=null)
            sql=sql+" Name_SL like ? and";
        if(sl.getDate_SL()!=null)
            sql=sql+" Date_SL = ? and";
        if(sl.getDescription_SL()!=null)
            sql=sql+" Description_SL like ? and";

        if(sql.endsWith("where "))
        {
            return null;
        }

        if(sql.endsWith("and"))
        {
            sql=sql.substring(0,sql.length()-3);//缩减
        }

        //查询
        List<SongList> slList=jdbc.query(sql,new Object[]{list.toArray()}, new BeanPropertyRowMapper(SongList.class));

        return slList;
    }

    @Override
    public List<SongList> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_songlist;

        //查询
        List<SongList> slList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(SongList.class));

        return slList;
    }

    @Override
    public int deleteByPK(SongList sl) {
        String sql_q="select * from "+ Set_StringConstants.table_songlist+" where ID_SL = ? ";

        //查询
        List<SongList> slList=jdbc.query(sql_q,new Object[]{sl.getId_SL()}, new BeanPropertyRowMapper(SongList.class));

        if(slList==null||slList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_songlist+"where ID_SL = "+sl.getId_SL();
        return jdbc.update(sql_d);
    }

    @Override
    public int update(SongList sl) {
        String sql="update "+Set_StringConstants.table_songlist+"set ";

        List<Object> list=sl.objectList();//获取非空项

        if(sl.getId_User()!=null)
        {
            sql+="ID_User = ? ,";
//            list.add(user.getPassword_User());
        }
        if(sl.getName_SL()!=null)
        {
            sql+=" Name_SL = ? ,";
//            list.add(user.getNickname_User());
        }
        if(sl.getDate_SL()!=null)
        {
            sql+=" Date_SL = ? ,";
//            list.add(user.getIcon_User());
        }
        if(sl.getDescription_SL()!=null)
        {
            sql+=" Description_SL = ? ,";
//            list.add(user.getIdiograph_User());
        }


        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_SL = ? ";
        list.add(sl.getId_SL());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新
    }

    @Override
    public int insertNew(SongList sl) {
        String sql="insert into "+Set_StringConstants.table_songlist+" values (?,?,?,?) ";


        return jdbc.update(sql,sl.getId_User(),sl.getName_SL(),sl.getDate_SL(),sl.getDescription_SL());
    }

    @Override
    public List<Song> queryAllSongBySL(SongList sl) {
        List<Song> songList = new ArrayList<Song>();

        String sql="select * from "+ Set_StringConstants.view_sl_s;

        if(sl.getId_SL()!=null)
            sql=sql+"where ID_SL like ?";
        else
            return null;

        //查询
        List<SL_S> sl_sList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(SL_S.class));


        for(SL_S sl_s:sl_sList){
            Song s = new Song();

            s.setId_Song(sl_s.getId_Song());
            s.setName_Song(sl_s.getName_Song());
            s.setReleaseDate_Song(sl_s.getDate_Release());
            s.setSinger_Song(sl_s.getSinger());
            s.setFileURL_Song(sl_s.getContent_Song());

            songList.add(s);
        }


        return songList;
    }
}
