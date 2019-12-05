package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.implement.DAO_Comment;
import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.Interface_Comment_server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.Double;

/**
 * @Author: mjj
 * @Date: Created in 00:59 2019/12/4
 * @Desprition: 评论业务实现类
 */
@Service(value = "CommmentService")
public class Service_Comment implements Interface_Comment_server {

    @Autowired
    private DAO_Comment dao_Comment;

    @Autowired
    private DAO_Song dao_Song;


    @Override
    public ResultEntity getRandom(Integer num) {
        ResultEntity resultEntity =new ResultEntity();

        if(num<=0||num>100)
        {
            resultEntity.setInfo_error("<ERROR> num must be in range[1,100]");
            return resultEntity;
        }

        resultEntity.setObject(dao_Comment.queryRandom(num));
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity write(Comment comment, Song song, User user) {
        //解析请求数据


        Boolean success = Boolean.FALSE;
        String errorMsg = "添加评论成功！";

        if(comment == null || song == null || user == null) {
            errorMsg = "请求数据不能为空！";
        }else if(comment.getScore_Comment()!=null && (comment.getScore_Comment() >100 || comment.getScore_Comment() <0)){
            errorMsg = "分数格式不符合，需在0～100";
        }else if ( song.getId_Song() ==null){
            errorMsg = "歌曲编号不能为空！";
        } else if(user.getId_User() == null || user.getId_User().equals("")){
            errorMsg = "用户编号不能为空！";
        } else if(comment.getReleaseTime_Comment()==null){
            errorMsg = "评论的发布日期不能为空！";
        } else{
            comment.setDislikes_Comment(0);
            comment.setLikes_Comment(0);
            comment.setId_User(user.getId_User());
            comment.setId_Song(song.getId_Song());

            Comment comment2 = new Comment();
            comment2.setId_Song(comment.getId_Song());
            List<Comment> commentList= dao_Comment.queryOrderbyLikes(comment2, null);
            int count = 0;
            Double sum =0.0;
            for(Comment c :commentList){
                if(c.getScore_Comment()!=null && c.getScore_Comment() >=0 ){
                    count++;
                    sum += c.getScore_Comment();
                }

            }
            if(comment.getScore_Comment()!=null){
                count++;
                sum += comment.getScore_Comment();
            }

            song.setScore(sum/count);

            //更新评论信息
            int resultRow = dao_Comment.insertNew(comment);
            if (resultRow < 1){
                errorMsg = "更新评论失败！";
            }else {
                resultRow = dao_Song.update(song);
                if (resultRow < 1){
                    errorMsg = "更新歌曲分数失败！";
                }else{
                    success = Boolean.TRUE;
                }

            }
        }



        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity modifyContent(Comment comment) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "更新评论内容成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if (comment.getContent_Comment() == null ){
            errorMsg = "评论内容不能为空！";
        } else {
            //更新用户信息
            int resultRow = dao_Comment.update(comment);
            if (resultRow < 1){
                errorMsg = "更新评论内容失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;

    }

    @Override
    public ResultEntity likes(Comment comment) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "评论点赞成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if (comment.getId_Comment() == null ){
            errorMsg = "评论id不能为空！";
        } else if(comment.getLikes_Comment() == null || comment.getLikes_Comment() < 0){
            errorMsg = "点赞数格式不对！";
        }else {

            comment.setScore_Comment(comment.getScore_Comment() + 1);
            //更新用户信息
            int resultRow = dao_Comment.update(comment);
            if (resultRow < 1){
                errorMsg = "更新评论点赞数失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity dislikes(Comment comment) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "评论踩成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if (comment.getId_Comment() == null ){
            errorMsg = "评论id不能为空！";
        } else if(comment.getLikes_Comment() == null || comment.getLikes_Comment() < 0){
            errorMsg = "踩格式不对！";
        }else {

            comment.setScore_Comment(comment.getScore_Comment() + 1);
            //更新用户信息
            int resultRow = dao_Comment.update(comment);
            if (resultRow < 1){
                errorMsg = "更新评论踩失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity modifyscore(Comment comment){

        Boolean success = Boolean.FALSE;
        String errorMsg = "修改分数成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if (comment.getId_Comment() == null ){
            errorMsg = "评论编号不能为空！";
        } else if(comment.getScore_Comment() <0 || comment.getLikes_Comment() > 100){
            errorMsg = "评论score格式不对！";
        }else {


            //更新用户信息
            int resultRow = dao_Comment.update(comment);
            if (resultRow < 1){
                errorMsg = "更新评论score失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity delete(Comment comment) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "歌曲删除成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if (comment.getId_Comment() == null){
            errorMsg = "评论编号不能为空！";
        }else{
            Comment comment2 = new Comment();
            comment2.setId_ReplyComment(comment.getId_ReplyComment());
            List<Comment> commentList = dao_Comment.queryOrderbyLikes(comment2, null);
            if(commentList.size()>0){
                errorMsg = "仍存在子评论";
            }else{
                Song song = new Song();

                song.setId_Song(comment.getId_Song());
                Comment comment3 = new Comment();
                comment2.setId_Song(comment.getId_Song());
                List<Comment> commentList2= dao_Comment.queryOrderbyLikes(comment2, null);
                int count = 0;
                Double sum =0.0;
                for(Comment c :commentList){

                    if(c.getScore_Comment()!=null && (c.getScore_Comment() >=0 || c.getScore_Comment() <=100)){
                        count ++;
                        sum += c.getScore_Comment();
                    }

                }
                if(comment.getScore_Comment()!=null && (comment.getScore_Comment() >=0 || comment.getScore_Comment() <=100)){
                    sum -= comment.getScore_Comment();
                    count--;
                }
                song.setScore(sum/count);

                //删除评论信息
                int resultRow = dao_Comment.deleteByPK(comment);
                if (resultRow < 1){
                    errorMsg = "删除评论失败！";
                }else {
                    resultRow = dao_Song.update(song);
                    if (resultRow < 1){
                        errorMsg = "更新歌曲分数失败！";
                    }else{
                        success = Boolean.TRUE;
                    }

                }
            }
        }


        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity queryByReply(Comment comment) {
        List<Comment> commentList = null;

        Boolean success = Boolean.FALSE;
        String errorMsg = "获取子评论信息成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        }else if (comment.getId_Comment() == null){
            errorMsg = "评论编号不能为空";
        }else {
            Comment comment2 = new Comment();

            comment2.setId_ReplyComment(comment.getId_Comment());
            //根据请求参数查询子评论
            commentList = dao_Comment.queryOrderbyTime(comment2, null);
            if (commentList == null && commentList.size()<0){
                errorMsg = "子评论信息不存在！";
            } else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg,commentList);
        return resultEntity;
    }

    @Override
    public ResultEntity queryBySong(Comment comment) {
        List<Comment> commentList = null;

        Boolean success = Boolean.FALSE;
        String errorMsg = "获取改歌曲下的评论信息成功！";
        if (comment == null){
            errorMsg = "请求数据不能为空！";
        } else if(comment.getId_Song() == null){
            errorMsg = "相应歌曲编号不能为空";
        }else {
            //根据请求参数查询歌曲信息
            commentList = dao_Comment.queryOrderbyTime(comment, null);

            if (commentList == null){
                errorMsg = "评论信息不存在！";
            } else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg,commentList);
        return resultEntity;
    }

    @Override
    public ResultEntity queryNew() {
        List<Comment> commentList = null;

        Boolean success = Boolean.FALSE;
        String errorMsg = "获取改歌曲下的评论信息成功！";


        //根据请求参数查询歌曲信息
        commentList = dao_Comment.queryAll();

        if (commentList == null){
            errorMsg = "评论信息不存在！";
        } else {
            success = Boolean.TRUE;
        }


        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg,commentList);
        return resultEntity;
    }
}
