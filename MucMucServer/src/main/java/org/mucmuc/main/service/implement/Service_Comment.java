package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.implement.DAO_Comment;
import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.Interface_Comment_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.Double;

/**
 * @Author: mjj
 * @Date: Created in 00:59 2019/12/4
 * @Desprition: 评论业务实现类
 */
@Service(value = "Service_Commment")
public class Service_Comment implements Interface_Comment_service {

    @Autowired
    private DAO_Comment dao_Comment;

    @Autowired
    private DAO_Song dao_Song;


    @Override
    public ResultEntity getRandom(Integer num) {
        ResultEntity resultEntity =new ResultEntity();

        try{
            if(num<=0||num>100)
            {
                resultEntity.setInfo_error("<ERROR> num must be in range[1,100]");
                return resultEntity;
            }

            resultEntity.setObject(dao_Comment.queryRandom(num));
            resultEntity.setState(true);
            return resultEntity;
        }
        catch (Exception e){
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获得随机评论失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity write(Comment comment, Song song, User user) {
        //解析请求数据

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";
            String opMsg="done";
            if(comment.getScore_Comment()==null|| (comment.getScore_Comment() >5 || comment.getScore_Comment() <0)){
                errorMsg = "分数格式不符合, 需在[0,5], 且不得为空";
            }else if ( song.getId_Song() ==null){
                errorMsg = "歌曲编号不能为空";
            } else if(user.getId_User() == null || user.getId_User().equals("")){
                errorMsg = "用户编号不能为空";
            } /*else if(comment.getReleaseTime_Comment()==null){
                errorMsg = "评论的发布日期不能为空";
            }*/ else{
                comment.setDislikes_Comment(0);
                comment.setLikes_Comment(0);
                comment.setId_User(user.getId_User());
                comment.setId_Song(song.getId_Song());

                //更新评论信息
                int resultRow = dao_Comment.insertNew(comment);
                if (resultRow < 1){
                    errorMsg = "更新评论失败";
                }else {
//                    List<Comment> commentList= dao_Comment.queryOrderbyLikes(comment2, null)
                    List<Comment> commentList= dao_Comment.queryAllUnderSong(song);
                    int count = 0;
                    Double sum =0.0;
                    for(Comment c :commentList){
                        if(c.getScore_Comment()!=null && c.getScore_Comment() >=0 ){
                            count++;
                            sum += c.getScore_Comment();
                        }
                    }

                    song.setScore(sum/count);

                    resultRow = dao_Song.update(song);
                    if (resultRow < 1){
                        errorMsg = "更新歌曲分数失败";
                    }else{
                        success = Boolean.TRUE;
                    }

                }
            }


            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg ,null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("添加评论失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity update(Comment comment) {

        try{
            Comment comment_db=null;
            Boolean success = Boolean.FALSE;
            String errorMsg ="";
            String opMsg="done";
            if (comment.getId_Comment() == null ){
                errorMsg = "<ERROR> 评论ID为空";
            } else {
                //更新用户信息
                int resultRow = dao_Comment.update(comment);
                if (resultRow < 1){
                    opMsg = "更新评论内容失败";
                }else {
                    success = Boolean.TRUE;
                    //从数据库中获取
                    comment_db=dao_Comment.queryByPK(comment);
                    Song song=new Song();
                    song.setId_Song(comment_db.getId_Song());
                    List<Comment> commentList= dao_Comment.queryAllUnderSong(song);
                    int count = 0;
                    Double sum =0.0;
                    for(Comment c :commentList){
                        if(c.getScore_Comment()!=null && c.getScore_Comment() >=0 ){
                            count++;
                            sum += c.getScore_Comment();
                        }
                    }

                    song.setScore(sum/count);

                    resultRow = dao_Song.update(song);
                    if (resultRow < 1){
                        errorMsg = "更新歌曲分数失败";
                    }else{
                        success = Boolean.TRUE;
                    }

                }
            }
            ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg, comment_db);
            return resultEntity;

        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("更新评论内容失败; 捕获到异常");
            return resultEntity;
        }
    }

    @Override
    public ResultEntity likes(Comment comment) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (comment.getId_Comment() == null ){
                errorMsg = "评论id不能为空";
            } /*else if(comment.getLikes_Comment() == null || comment.getLikes_Comment() < 0){
                errorMsg = "点赞数小于0或为空";
            }*/else {

                //先从数据库中查询
                Comment comment_db=dao_Comment.queryByPK(comment);
                comment_db.setLikes_Comment(comment_db.getLikes_Comment() + 1);
                //更新用户信息
                int resultRow = dao_Comment.update(comment_db);
                if (resultRow < 1){
                    opMsg = "更新评论点赞数失败";
                }else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("点赞失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity dislikes(Comment comment) {
        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (comment.getId_Comment() == null ){
                errorMsg = "评论id不能为空";
            }/* else if(comment.getDislikes_Comment() == null || comment.getDislikes_Comment() < 0){
                errorMsg = "踩数小于0或为空";
            }*/else {
                //先从数据库中查询
                Comment comment_db=dao_Comment.queryByPK(comment);
                comment_db.setDislikes_Comment(comment_db.getDislikes_Comment() + 1);
                //更新用户信息
                int resultRow = dao_Comment.update(comment_db);
                if (resultRow < 1){
                    opMsg = "更新评论踩失败";
                }else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("踩操作失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity modifyscore(Comment comment){

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (comment == null){
                errorMsg = "请求数据不能为空";
            } else if (comment.getId_Comment() == null ){
                errorMsg = "评论编号不能为空";
            } else if(comment.getScore_Comment() <0 || comment.getScore_Comment() > 100){
                errorMsg = "评论score格式不对";
            }else {
                //更新用户信息
                int resultRow = dao_Comment.update(comment);
                if (resultRow < 1){
                    opMsg = "更新评论score失败";
                }else {
                    Comment comment2 = new Comment();
                    comment2.setId_Song(comment.getId_Song());
                    List<Comment> commentList= dao_Comment.queryOrderbyTime(comment2, null);
                    int count = 0;
                    Double sum =0.0;
                    for(Comment c :commentList){
                        if(c.getScore_Comment()!=null && c.getScore_Comment() >=0 ){
                            count++;
                            sum += c.getScore_Comment();
                        }

                    }
                    Song song = new Song();
                    if(count == 0){
                        song.setScore(0.0);
                    }
                    else{
                        song.setScore(sum/count);
                    }

                    song.setId_Song(comment.getId_Song());

                    resultRow = dao_Song.update(song);
                    if (resultRow < 1){
                        errorMsg = "更新歌曲分数失败";
                    }else{
                        success = Boolean.TRUE;
                    }

                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("更新评论score失败！");
            return resultEntity;

        }

    }

    @Override
    public ResultEntity delete(Comment comment) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (comment == null){
                errorMsg = "请求数据不能为空";
            } else if (comment.getId_Comment() == null){
                errorMsg = "评论编号不能为空";
            }else{
//                List<Comment> commentList = dao_Comment.queryOrderbyLikes(comment2, null);
                List<Comment> commentList = dao_Comment.queryReply(comment);
                if(commentList.size()>0){
                    errorMsg = "仍存在子评论";
                }else{
                    Song song = new Song();

                    song.setId_Song(comment.getId_Song());

                    //删除评论信息
                    int resultRow = dao_Comment.deleteByPK(comment);
                    if (resultRow < 1){
                        opMsg = "删除评论失败";
                    }else {
                        Comment comment3 = new Comment();
                        comment3.setId_Song(comment.getId_Song());
//                        List<Comment> commentList2= dao_Comment.queryOrderbyLikes(comment3, null);
                        List<Comment> commentList2= dao_Comment.queryAllUnderSong(song);
                        int count = 0;
                        Double sum =0.0;
                        for(Comment c :commentList2){

                            if(c.getScore_Comment()!=null && (c.getScore_Comment() >=0 && c.getScore_Comment() <=100)){
                                count ++;
                                sum += c.getScore_Comment();
                            }

                        }
                        if(count!=0)
                            song.setScore(sum/count);
                        else
                            song.setScore(0.0);
                        resultRow = dao_Song.update(song);
                        if (resultRow < 1){
                            opMsg = "更新歌曲分数失败";
                        }else{
                            success = Boolean.TRUE;
                        }

                    }
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("删除评论失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity queryByReply(Comment comment) {

        try{
            List<Comment> commentList = null;

            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
           if (comment.getId_Comment() == null){
                errorMsg = "<ERROR >评论编号不能为空";
            }else {
                Comment comment2 = new Comment();

                comment2.setId_Reply(comment.getId_Comment());
                //根据请求参数查询子评论
                commentList = dao_Comment.queryOrderbyTime(comment2, null);
                if (commentList == null && commentList.size()<0){
                    opMsg = "子评论信息不存在";
                } else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, commentList);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获取子评论失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity getCommentsUnderSong(Song song) {

        try {
            List<Comment> commentList = null;

            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if(song.getId_Song() == null){
                errorMsg = "相应歌曲编号不能为空";
            }else {
                //根据请求参数查询歌曲信息
//                commentList = dao_Comment.queryOrderbyTime(comment, null);
                commentList=dao_Comment.queryAllUnderSong(song);
                success = Boolean.TRUE;
                if (commentList.size()==0){
                    opMsg = "评论信息不存在";
                }
                success=Boolean.TRUE;
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, commentList);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获取歌曲下的评论失败！");
            return resultEntity;
        }
    }

    @Override
    public ResultEntity queryNew() {

        try{
            List<Comment> commentList = null;

            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成

            //根据请求参数查询歌曲信息
            commentList = dao_Comment.queryAll();

            if (commentList.size()==0){
                opMsg = "评论信息不存在";
            } else {
                success = Boolean.TRUE;
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, commentList);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获得所有评论失败！");
            return resultEntity;

        }

    }
}
