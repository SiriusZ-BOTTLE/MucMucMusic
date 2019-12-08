package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.implement.DAO_Lyrics;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.service.Interface_Lyrics_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: mjj
 * @Date: Created in 20:52 2019/12/3
 * @Desprition: 歌词业务实现类
 */
@Service(value = "Service_Lyrics")
public class Service_Lyrics implements Interface_Lyrics_service {

    @Autowired
    private DAO_Lyrics dao_Lyrics;


    @Override
    public ResultEntity get(Lyrics lyrics) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
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
            ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, lyrics);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获得歌词信息失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity update(Lyrics lyrics) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (lyrics == null){
                errorMsg = "请求数据不能为空！";
            } else if (lyrics.getId_Lyrics() == null || lyrics.getId_Lyrics().equals("")){
                errorMsg = "歌词编号不能为空！";
            }else {
                //更新用户信息
                int resultRow = dao_Lyrics.update(lyrics);
                if (resultRow < 1){
                    opMsg = "更新歌词失败！";
                }else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg , null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("更新歌词失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity delete(Lyrics lyrics) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (lyrics == null){
                errorMsg = "请求数据不能为空！";
            } else if (lyrics.getId_Song() == null){
                errorMsg = "歌曲编号不能为空！";
            } else {
                //删除歌词信息
                int resultRow = dao_Lyrics.deleteBySong(lyrics);
                if (resultRow < 1){
                    opMsg = "删除歌词失败！";
                }else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg , null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("删除歌词失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity add(Lyrics lyrics) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (lyrics == null){
                errorMsg = "请求数据不能为空！";
            } else if (lyrics.getFlag_Pure_Lyrics() == Boolean.TRUE){
                errorMsg = "为纯音乐，不需要歌词！";
            } else {

                //插入用户信息
                int resultRow = dao_Lyrics.insertNew(lyrics);
                if (resultRow < 1){
                    opMsg = "添加歌词失败！";
                }else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success, errorMsg,opMsg , null);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("添加歌词信息！");
            return resultEntity;
        }

    }
}
