package org.mucmuc.main.DAO.implement;

import org.mucmuc.main.DAO.Interface_Comment_DAO;
import org.mucmuc.main.DAO.Set_StringConstants;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
public class DAO_Comment implements Interface_Comment_DAO {


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Comment queryByPK(Comment comment) {
        String sql="select * from "+ Set_StringConstants.table_comment +" where ID_Comment = ? ";
        //查询
        List<Comment> commentList=jdbc.query(sql,new Object[]{comment.getId_Comment()}, new BeanPropertyRowMapper(Comment.class));

        if(commentList==null||commentList.size()==0)
            return null;

        return commentList.get(0);
    }

    @Override
    public List<Comment> queryRandom(Integer num) {

        String sql=" select * from "+Set_StringConstants.table_comment+" order by rand() limit 0,"+num;
        List<Comment> commentList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Comment.class));

        return commentList;
    }

    @Override
    public List<Comment> queryOrderbyTime(Comment comment1,Comment comment2) {

        String sql="select * from "+ Set_StringConstants.table_comment + " where";

        List<Object> list=new ArrayList<Object>();

        if(comment1.getContent_Comment()!=null) {
            sql=sql+" Content_Comment like ? and";
            list.add("%"+comment1.getContent_Comment()+"%");
        }

        if(comment1.getDislikes_Comment()!=null){
            sql=sql+" Dislikes_Comment >= ? and Dislikes_Comment < ? and";
            list.add(comment1.getDislikes_Comment());
            list.add(comment2.getDislikes_Comment());
        }

        if(comment1.getId_Reply()!=null){
            sql=sql+" ID_Reply = ? and";
            list.add(comment1.getId_Reply());
        }

        if(comment1.getId_Song()!=null){
            sql=sql+" ID_Song = ? and";
            list.add(comment1.getId_Song());
        }

        if(comment1.getId_User()!=null){
            sql=sql+" ID_User = ? and";
            list.add(comment1.getId_User());
        }

        if(comment1.getLikes_Comment()!=null&&comment2!=null&&comment2.getLikes_Comment()!=null){
            sql=sql+" Likes_Comment >= ? and Likes_Comment < ? and";
            list.add(comment1.getLikes_Comment());
            list.add(comment2.getLikes_Comment());
        }

        if(comment1.getReleaseTime_Comment()!=null){
            sql=sql+" ReleaseTime_Comment = ? and";
            list.add(comment1.getReleaseTime_Comment());
        }

        if(comment1.getScore_Comment()!=null&&comment2!=null&&comment2.getScore_Comment()!=null){
            sql=sql+" Score_Comment >= ? and Score_Comment < ? and";
            list.add(comment1.getScore_Comment());
            list.add(comment2.getScore_Comment());
        }


//        if(sql.endsWith("where "))
//        {
//            return null;
//        }

        if(sql.endsWith("and"))
        {
            sql=sql.substring(0,sql.length()-3) + " order by ReleaseTime_Comment DESC";//缩减
        }
        //查询
        List<Comment> commentList=jdbc.query(sql,new Object[]{comment1.getId_Song()}, new BeanPropertyRowMapper(Comment.class));

        return commentList;
    }

    @Override
    public List<Comment> queryAllUnderSong(Song song) {
        String sql="select * from "+ Set_StringConstants.table_comment+"  where ID_Song = ? order by ReleaseTime_Comment DESC";

        List<Comment> list=jdbc.query(sql,new Object[]{song.getId_Song()},new BeanPropertyRowMapper(Comment.class));

        return list;
    }

    @Override
    public List<Comment> queryReply(Comment comment) {
        String sql="select * from "+ Set_StringConstants.table_comment+" where id_Reply = ? ";

        List<Comment> list=jdbc.query(sql,new Object[]{comment.getId_Comment()},new BeanPropertyRowMapper(Comment.class));
        return list;
    }

    @Override
    public List<Comment> queryOrderbyLikes(Comment comment1,Comment comment2) {

        String sql="select * from "+ Set_StringConstants.table_comment + " where";

        List<Object> list=new ArrayList<Object>();

        if(comment1.getContent_Comment()!=null) {
            sql=sql+" Content_Comment like ? and";
            list.add("%"+comment1.getContent_Comment()+"%");
        }

        if(comment1.getDislikes_Comment()!=null){
            sql=sql+" Dislikes_Comment >= ? and Dislikes_Comment < ? and";
            list.add(comment1.getDislikes_Comment());
            list.add(comment2.getDislikes_Comment());
        }

        if(comment1.getId_Reply()!=null){
            sql=sql+" ID_Reply = ? and";
            list.add(comment1.getId_Reply());
        }

        if(comment1.getId_Song()!=null){
            sql=sql+" ID_Song = ? and";
            list.add(comment1.getId_Song());
        }

        if(comment1.getId_User()!=null){
            sql=sql+" ID_User = ? and";
            list.add(comment1.getId_User());
        }

        if(comment1.getLikes_Comment()!=null&&comment2!=null&&comment2.getLikes_Comment()!=null){
            sql=sql+" Likes_Comment >= ? and Likes_Comment < ? and";
            list.add(comment1.getLikes_Comment());
            list.add(comment2.getLikes_Comment());
        }

        if(comment1.getReleaseTime_Comment()!=null){
            sql=sql+" ReleaseTime_Comment = ? and";
            list.add(comment1.getReleaseTime_Comment());
        }

        if(comment1.getScore_Comment()!=null&&comment2!=null&&comment2.getScore_Comment()!=null){
            sql=sql+" Score_Comment >= ? and Score_Comment < ? and";
            list.add(comment1.getScore_Comment());
            list.add(comment2.getScore_Comment());
        }


//        if(sql.endsWith("where "))
//        {
//            return null;
//        }

        if(sql.endsWith("and"))
        {
            sql=sql.substring(0,sql.length()-3) + " order by Likes_Comment DESC";//缩减
        }
        //查询
        List<Comment> commentList=jdbc.query(sql,new Object[]{list.toArray()}, new BeanPropertyRowMapper(Comment.class));

        return commentList;
    }

    @Override
    public List<Comment> queryAll() {

        String sql="select * from "+ Set_StringConstants.table_comment;

        //查询
        List<Comment> commentList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(Comment.class));

        return commentList;
    }

    @Override
    public int deleteByPK(Comment comment) {
        String sql_q="select * from "+ Set_StringConstants.table_comment+" where ID_Comment= ? ";

        //查询
        List<Comment> commentList=jdbc.query(sql_q,new Object[]{comment.getId_Comment()}, new BeanPropertyRowMapper(Comment.class));

        if(commentList==null||commentList.size()==0)
            return 0;
        //删除
        String sql_d="delete from "+Set_StringConstants.table_comment+" where ID_Comment = "+comment.getId_Comment();
        return jdbc.update(sql_d);
    }

    @Override
    public int deleteByAttribute(Comment comment1, Comment comment2) {

        String sql="select * from "+ Set_StringConstants.table_comment + " where ";

        List<Object> list=new ArrayList<Object>();

        if(comment1.getContent_Comment()!=null) {
            sql=sql+" Content_Comment like ? and";
            list.add("%"+comment1.getContent_Comment()+"%");
        }

        if(comment1.getDislikes_Comment()!=null){
            sql=sql+" Dislikes_Comment >= ? and Dislikes < ? and";
            list.add(comment1.getDislikes_Comment());
            list.add(comment2.getDislikes_Comment());
        }

        if(comment1.getId_Reply()!=null){
            sql=sql+" ID_Reply = ? and";
            list.add(comment1.getId_Reply());
        }

        if(comment1.getId_Song()!=null){
            sql=sql+" ID_Song = ? and";
            list.add(comment1.getId_Song());
        }

        if(comment1.getId_User()!=null){
            sql=sql+" ID_User = ? and";
            list.add(comment1.getId_User());
        }

        if(comment1.getLikes_Comment()!=null){
            sql=sql+" Likes_Comment >= ? and Likes < ? and";
            list.add(comment1.getLikes_Comment());
            list.add(comment2.getLikes_Comment());
        }

        if(comment1.getReleaseTime_Comment()!=null){
            sql=sql+" ReleaseTime_Comment = ? and";
            list.add(comment1.getReleaseTime_Comment());
        }

        if(comment1.getScore_Comment()!=null){
            sql=sql+" Score_Comment >= ? and Score_Comment < ? and";
            list.add(comment1.getScore_Comment());
            list.add(comment2.getScore_Comment());
        }

        if(comment1.getMood()!=null){
            sql=sql+" mood = ? and";
            list.add(comment1.getMood());
        }


//        if(sql.endsWith("where "))
//        {
//            return 0;
//        }

        if(sql.endsWith("and"))
        {
            sql=sql.substring(0,sql.length()-3);//缩减
        }

        //查询
        List<Comment> commentList=jdbc.query(sql,new Object[]{list.toArray()}, new BeanPropertyRowMapper(Comment.class));//若未找到，则返回空list

        if(commentList==null||commentList.size()==0)
            return 0;

        String sql_d = "delete " + sql.substring(9,-1);

        return jdbc.update(sql_d);
    }

    @Override
    public int update(Comment comment) {
        String sql="update "+Set_StringConstants.table_comment+" set ";

        List<Object> list=comment.objectList_notNull();//获取非空项

        if(comment.getId_Song()!=null)
        {
            sql+="ID_Song = ? ,";
//            list.add(lyrics.getID_Song());
        }
        if(comment.getId_User()!=null)
        {
            sql+="ID_User = ? ,";
//            list.add(lyrics.getContent_Lyrics());
        }
        if(comment.getId_Reply()!=null)
        {
            sql+="ID_Reply = ? ,";
//            list.add(lyrics.getFlag_Pure_Lyrics());
        }
        if(comment.getContent_Comment()!=null)
        {
            sql+="Content_Comment = ? ,";
        }
        if(comment.getReleaseTime_Comment()!=null)
        {
            sql+="ReleaseTime_Comment = ? ,";
        }
        if(comment.getLikes_Comment()!=null){
            sql+="Likes_Comment = ? ,";
        }
        if(comment.getDislikes_Comment()!=null){
            sql+="Dislikes_Comment = ? ,";
        }
        if(comment.getScore_Comment()!=null) {
            sql += "Score_Comment = ? ,";
        }
        if(comment.getMood()!=null) {
            sql += "mood = ? ,";
        }

        if(sql.endsWith(","))
        {
            sql=sql.substring(0,sql.length()-1);//缩减
        }
        sql+=" where ID_Comment = ? ";
        list.add(comment.getId_Comment());
        //查询
//            List<User> userList=jdbc.query(sql,new Object[]{}, new BeanPropertyRowMapper(User.class));
        return jdbc.update(sql,list.toArray());//执行更新
    }

    @Override
    public int insertNew(Comment comment) {

        String sql="insert into "+Set_StringConstants.table_comment+" values (null,?,?,?,CURRENT_TIME,?,?,?,?,?) ";

        return jdbc.update(sql,comment.getId_Song(),comment.getId_User(),comment.getId_Reply(),comment.getContent_Comment(),comment.getLikes_Comment(),comment.getDislikes_Comment(),comment.getScore_Comment(),comment.getMood());//这个简洁点

    }
}
