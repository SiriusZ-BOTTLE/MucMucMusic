package org.mucmuc.main.service.implement;



import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.service.Interface_Song_server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 21:40 2019/3/12
 * @Desprition: 歌曲业务实现类
 */
@Service(value = "Service_Song")
public class Service_Song implements Interface_Song_server {

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

        resultEntity.setObject(dao_Song.queryRandom(num));
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity play(Song song) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！";
        } else {
            //根据请求参数查询用户信息
            song = dao_Song.queryByPK(song);
            if (song == null){
                opMsg = "歌曲不存在！";
            }else if(song.getFile_Song() == null){
                opMsg = "歌曲内容信息不存在！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, song);
        return resultEntity;
    }

    @Override
    public ResultEntity add(Song song) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！"; //发行日期的""判断
        } else if(song.getName_Song() == null || song.getSinger_Song() == null || song.getReleaseDate_Song() == null || song.getName_Song().equals("") || song.getSinger_Song().equals("")){
            errorMsg = "歌曲名称或歌手名称或发行日期不能为空";
        }else if(song.getScore()<0){
            errorMsg= "歌曲分数不能为负数";
        }
        else{
            int resultRow = dao_Song.insertNew(song);
            if (resultRow < 1){
                opMsg = "插入失败！";
            } else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity delete(Song song) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！";
        } else if (song.getId_Song() == null){
            errorMsg = "用户编号不能为空！";
        } else {
            //删除用户信息
            int resultRow = dao_Song.deleteByPK(song);
            if (resultRow < 1){
                opMsg = "删除歌曲失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg , null);
        return resultEntity;
    }

    @Override
    public ResultEntity updateInfo(Song song) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！";
        } else if (song.getId_Song() == null || song.getId_Song().equals("")){
            errorMsg = "用户编号不能为空！";
        } else if (song.getScore()<0){
            errorMsg = "分数不能为负数！";
        }else {
            //更新用户信息
            int resultRow = dao_Song.update(song);
            if (resultRow < 1){
                opMsg = "更新用户失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, opMsg, null);
        return resultEntity;

    }

    @Override
    public ResultEntity queryBySong(Song song) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！";
        } else {
            //根据请求参数查询歌曲信息
            song = dao_Song.queryByPK(song);

            if (song == null){
                opMsg = "歌曲信息不存在！";
            } else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, song);
        return resultEntity;
    }

    @Override
    public ResultEntity queryTagBySong(Song song) {
        List<Tag> tagList = null;

        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        if (song == null){
            errorMsg = "请求数据不能为空！";
        } else {
            //根据请求参数查询歌曲信息
            tagList = dao_Song.queryTagsbySong(song);

            if (tagList == null || tagList.size() == 0){
                opMsg = "标签信息不存在！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, tagList);
        return resultEntity;
    }
}
