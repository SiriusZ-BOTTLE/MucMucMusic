package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Song_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class DAO_Song implements Interface_Song_DAO {


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Song queryByPK(Song song) {

        String sql="select * from "+ Set_StringConstants.table_song +" where ID_Song = ? ";
        //查询
        List<Song> songList=jdbc.query(sql,new Object[]{song.getID_Song()}, new BeanPropertyRowMapper(Song.class));

        if(songList==null||songList.size()==0)
            return null;

        return songList.get(0);
    }

    @Override
    public List<Song> queryByAttribute(Song song) {
        return null;
    }

    @Override
    public List<Song> queryAll() {
        return null;
    }

    @Override
    public int deleteByPK(Song song) {
        return 0;
    }

    @Override
    public int update(Song song) {
        return 0;
    }

    @Override
    public int insertNew(Song song) {
        return 0;
    }

    @Override
    public List<Tag> queryTagsbySong(Song song) {
        return null;
    }
}
