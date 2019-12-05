package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Tag_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.entity.Tag_Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DAO_Tag implements Interface_Tag_DAO {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Tag queryByPK(Tag tag) {

        String sql="select * from "+ Set_StringConstants.table_tag +" where ID_Tag = ? ";
        //查询
        List<Tag> tagList=jdbc.query(sql,new Object[]{tag.getId_Tag()}, new BeanPropertyRowMapper(Tag.class));

        if(tagList==null||tagList.size()==0)
            return null;

        return tagList.get(0);
    }

    @Override
    public List<Tag> queryRandom(Integer num) {
        String sql=" select * from "+Set_StringConstants.table_tag+" order by rand() limit 0,"+num;
        List<Tag> list=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Tag.class));

        return list;
    }

    @Override
    public List<Tag> queryByAttribute(Tag tag) {

        String sql="select * from "+ Set_StringConstants.table_tag;

        if(tag.getName_Tag()!=null)
            sql=sql+"where Name_Tag like ?";
        else
            return null;

        //查询
        List<Tag> tagList=jdbc.query(sql,new Object[]{tag.getName_Tag()}, new BeanPropertyRowMapper(Tag.class));

        return tagList;
    }

    @Override
    public List<Tag> queryAll() {
        String sql="select * from "+ Set_StringConstants.table_tag;

        //查询
        List<Tag> tagList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Tag.class));

        return tagList;
    }

    @Override
    public int deleteByPK(Tag tag) {

        String sql_q="select * from "+ Set_StringConstants.table_tag+" where ID_Tag = ? ";

        //查询
        List<Tag> tagList=jdbc.query(sql_q,new Object[]{tag.getId_Tag()}, new BeanPropertyRowMapper(Tag.class));

        if(tagList==null||tagList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_tag+"where ID_Tag = "+tag.getId_Tag();
        return jdbc.update(sql_d);
    }

    @Override
    public int update(Tag tag) {

        String sql="update "+Set_StringConstants.table_tag+"set ";

        List<Object> list=tag.objectList();//获取非空项


        if(tag.getName_Tag()!=null)
        {
            sql+="Name_Tag= ? ,";
//            list.add(user.getState_User());
        }

        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_Tag = ? ";
        list.add(tag.getName_Tag());
        list.add(tag.getId_Tag());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新

    }

    @Override
    public int insertNew(Tag tag) {

        String sql="insert into "+Set_StringConstants.table_tag+" values (?) ";

        //以下两句效果相同
        return jdbc.update(sql,tag.getName_Tag());//这个简洁点
//        return jdbc.update(sql,user.getID_User(),user.getPassword_User(),user.getNickname_User(),user.getIcon_User(),user.getIdiograph_User(),user.getGender_User(),user.getLevel_User(),user.getState_User());
    }

    @Override
    public List<Song> querySongsbyTag(Tag tag){

        List<Song> songList = new ArrayList<Song>();

        String sql="select * from "+ Set_StringConstants.view_tag_song;

        if(tag.getName_Tag()!=null)
            sql=sql+"where Name_Tag like ?";
        else
            return null;

        //查询
        List<Tag_Song> tag_songList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Tag_Song.class));


        for(Tag_Song tag_song:tag_songList){
            Song s = new Song();

            s.setId_Song(tag_song.getId_Song());
            s.setName_Song(tag_song.getName_Song());
            s.setReleaseDate_Song(tag_song.getDate_Release());
            s.setSinger_Song(tag_song.getSinger());
            s.setFile_Song(tag_song.getContent_Song());

            songList.add(s);
        }


        return songList;

    }


//    public static void main(String args[])
//    {
//        DAO_Tag userDAO=new DAO_Tag();
//        Tag tag=new Tag();
//        List<Tag> list=userDAO.queryAll();
//
//        System.out.println(list);
//
//    }

}
