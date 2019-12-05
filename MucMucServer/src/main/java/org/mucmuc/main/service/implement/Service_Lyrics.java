package org.mucmuc.main.service.implement;


import com.alibaba.fastjson.JSONObject;
import org.mucmuc.main.DAO.implement.DAO_Lyrics;
import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.service.Interface_Lyrics_server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: mjj
 * @Date: Created in 20:52 2019/12/3
 * @Desprition: 歌词业务实现类
 */
@Service(value = "Service_Lyrics")
public class Service_Lyrics implements Interface_Lyrics_server {

    @Autowired
    private DAO_Lyrics dao_Lyrics;


    @Override
    public ResultEntity getLyrics(Lyrics lyrics) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "获取歌词信息成功！";
        if (lyrics == null){
            errorMsg = "请求数据不能为空！";
        } else {
            //根据请求参数查询用户信息
            lyrics = dao_Lyrics.queryByID_Song(lyrics);
            if (lyrics == null){
                errorMsg = "歌词信息不存在！";
            }else if(lyrics.getFlag_Pure_Lyrics() == Boolean.TRUE){
                errorMsg = "为纯音乐，歌词不存在！";
            }else if(lyrics.getContent_Lyrics() == null || lyrics.getContent_Lyrics().equals("")){
                errorMsg = "歌词为空！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg,lyrics);
        return resultEntity;
    }

    @Override
    public ResultEntity update(Lyrics lyrics) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "更新歌词信息成功！";
        if (lyrics == null){
            errorMsg = "请求数据不能为空！";
        } else if (lyrics.getId_Lyrics() == null || lyrics.getId_Lyrics().equals("")){
            errorMsg = "歌词编号不能为空！";
        }else {
            //更新用户信息
            int resultRow = dao_Lyrics.update(lyrics);
            if (resultRow < 1){
                errorMsg = "更新歌词失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity delete(Lyrics lyrics) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "歌词删除成功！";
        if (lyrics == null){
            errorMsg = "请求数据不能为空！";
        } else if (lyrics.getId_Song() == null){
            errorMsg = "歌曲编号不能为空！";
        } else {
            //删除歌词信息
            int resultRow = dao_Lyrics.deleteBySong(lyrics);
            if (resultRow < 1){
                errorMsg = "删除歌词失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }

    @Override
    public ResultEntity add(Lyrics lyrics) {

        Boolean success = Boolean.FALSE;
        String errorMsg = "添加歌词成功！";
        if (lyrics == null){
            errorMsg = "请求数据不能为空！";
        } else if (lyrics.getFlag_Pure_Lyrics() == Boolean.TRUE){
            errorMsg = "为纯音乐，不需要歌词！";
        } else {

            //插入用户信息
            int resultRow = dao_Lyrics.insertNew(lyrics);
            if (resultRow < 1){
                errorMsg = "添加歌词失败！";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success, errorMsg, null);
        return resultEntity;
    }
}
