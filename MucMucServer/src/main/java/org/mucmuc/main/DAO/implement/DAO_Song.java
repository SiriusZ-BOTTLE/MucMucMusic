package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Song_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DAO_Song implements Interface_Song_DAO {


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Song queryByPK(Song song) {

        String sql="select * from "+ Set_StringConstants.table_song +" where ID_Song = ? ";
        //查询
        List<Song> songList=jdbc.query(sql,new Object[]{song.getId_Song()}, new BeanPropertyRowMapper(Song.class));

        if(songList==null||songList.size()==0)
            return null;

        return songList.get(0);
    }

    @Override
    public List<Song> queryRandom(Integer num) {
        String sql=" select * from "+Set_StringConstants.table_song+" order by rand() limit 0,"+num;
        List<Song> list=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Song.class));

        return list;
    }

    @Override
    public List<Song> queryByName(Song song) {

        String sql="select * from "+ Set_StringConstants.table_song + " where ";

//        List<Object> list = song.objectList_notNull();//获取非空项

        if(song.getName_Song()!=null)
            sql=sql+" Name_Song like ? and";
        else
            return null;

//        if(sql.endsWith("where "))
//        {
//            return null;
//        }
//
//        if(sql.endsWith("and"))
//        {
//            sql=sql.substring(0,sql.length()-3);//缩减
//        }

        //查询
        List<Song> songList=jdbc.query(sql,new Object[]{"%"+song.getName_Song()+"%"}, new BeanPropertyRowMapper(Song.class));

        return songList;
    }

    @Override
    public List<Song> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_song;

        //查询
        List<Song> songList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Song.class));

        return songList;
    }

    @Override
    public int deleteByPK(Song song) {
        String sql_q="select * from "+ Set_StringConstants.table_song+" where ID_Song = ? ";

        //查询
        List<Song> songList=jdbc.query(sql_q,new Object[]{song.getId_Song()}, new BeanPropertyRowMapper(Song.class));

        if(songList==null||songList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_song+"where ID_Song = "+song.getId_Song();
        return jdbc.update(sql_d);
    }

    @Override
    public int update(Song song) {
        String sql="update "+Set_StringConstants.table_song+"set ";

        List<Object> list=song.objectList_notNull();//获取非空项

        if(song.getName_Song()!=null)
        {
            sql+="Name_Song = ? ,";
//            list.add(user.getPassword_User());
        }
        if(song.getSinger_Song()!=null)
        {
            sql+=" Singer_Song = ? ,";
//            list.add(user.getNickname_User());
        }
        if(song.getReleaseDate_Song()!=null)
        {
            sql+=" ReleaseDate_Song = ? ,";
//            list.add(user.getIcon_User());
        }
        if(song.getFile_Song()!=null)
        {
            sql+=" File_Song = ? ,";
//            list.add(user.getIdiograph_User());
        }
        if(song.getIconFile_Song()!=null)
        {
            sql+=" IconFile_Song = ? ,";
        }
        if(song.getScore()!=null)
        {
            sql+=" Score_Song = ? ,";
//            list.add(user.getIdiograph_User());
        }


        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_Song = ? ";
        list.add(song.getId_Song());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新
    }

    @Override
    public int insertNew(Song song) {
        String sql="insert into "+Set_StringConstants.table_song+" values (?,?,?,?,?,?) ";


        return jdbc.update(sql,song.getName_Song(),song.getSinger_Song(),song.getReleaseDate_Song(),song.getFile_Song(),song.getIconFile_Song(),song.getScore());
    }

    @Override
    public List<Tag> queryTagsbySong(Song song) {
        List<Tag> tagList = new ArrayList<Tag>();

        String sql="select * from "+ Set_StringConstants.view_song_tag;

        if(song.getId_Song()!=null)
            sql=sql+" where ID_Song = ?";
        else
            return null;

        //查询
        List<Song_Tag> song_tagList=jdbc.query(sql,new Object[]{song.getId_Song()}, new BeanPropertyRowMapper(Song_Tag.class));


        for(Song_Tag song_tag:song_tagList){
            Tag t = new Tag();

            t.setName_Tag(song_tag.getName_Tag());

            tagList.add(t);
        }


        return tagList;
    }

    public List<SongList> querySongListsbySong(Song song){
        List<SongList> slList = new ArrayList<SongList>();

        String sql="select * from "+ Set_StringConstants.view_s_sl;

        if(song.getId_Song()!=null)
            sql=sql+"where ID_Song = ?";
        else
            return null;

        //查询
        List<S_SL> s_slList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(S_SL.class));


        for(S_SL s_sl:s_slList){
            SongList sl = new SongList();

            sl.setId_SL(s_sl.getId_SL());
            sl.setId_User(s_sl.getId_User());
            sl.setName_SL(s_sl.getName_SL());
            sl.setDate_SL(s_sl.getDate_SL());
            sl.setDescription_SL(s_sl.getDescription_SL());

            slList.add(sl);
        }


        return slList;

    }
}
