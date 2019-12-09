package org.mucmuc.main.service.implement;



import org.mucmuc.main.DAO.implement.DAO_Map_SL_S;
import org.mucmuc.main.DAO.implement.DAO_Map_S_T;
import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.DAO.implement.DAO_SongList;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.service.Interface_Song_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 21:40 2019/3/12
 * @Desprition: 歌曲业务实现类
 */
@Service(value = "Service_Song")
public class Service_Song implements Interface_Song_service {

    @Autowired
    private DAO_Song songDao;
    @Autowired
    private DAO_Map_SL_S map_sl_s_Dao;
    @Autowired
    private DAO_Map_S_T map_s_t_Dao;
    @Autowired
    private DAO_SongList songListDao;

    @Override
    public ResultEntity getRandom(Integer num) {
        ResultEntity resultEntity =new ResultEntity();

        try {
            if(num<=0||num>100)
            {
                resultEntity.setInfo_error("<ERROR> num must be in range[1,100]");
                return resultEntity;
            }

            resultEntity.setObject(songDao.queryRandom(num));
            resultEntity.setState(true);
            return resultEntity;
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("随机获得歌曲失败！");
            return resultEntity;
        }

    }

    @Override
    @Deprecated
    public ResultEntity play(Song song) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (song == null){
                errorMsg = "请求数据不能为空！";
            } else {
                //根据请求参数查询用户信息
                song = songDao.queryByPK(song);
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
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("播放失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity search(Song song) {
        ResultEntity resultEntity=new ResultEntity();

        try{
            if(song.getName_Song()==null)
            {
                resultEntity.setInfo_error("<ERROR> name_Song is NULL");
                return resultEntity;
            }

            List<Song> list=songDao.queryByName(song);

            resultEntity.setState(true);
            resultEntity.setObject(list);
            return resultEntity;
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("搜索歌曲信息失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity add(Song song) {

        try{
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
                int resultRow = songDao.insertNew(song);
                if (resultRow < 1){
                    opMsg = "插入失败！";
                } else {
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
            resultEntity.setInfo_error("添加歌曲失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity delete(Song song) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (song == null){
                errorMsg = "请求数据不能为空！";
            } else if (song.getId_Song() == null){
                errorMsg = "用户编号不能为空！";
            } else {
                //删除用户信息

                //先删除中间映射
                map_sl_s_Dao.deleteBySongID(song.getId_Song());
                map_s_t_Dao.deleteBySongID(song.getId_Song());
                int resultRow = songDao.deleteByPK(song);
                if (resultRow < 1){
                    opMsg = "删除歌曲失败！";
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
            resultEntity.setInfo_error("删除歌曲失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity update(Song song) {

        try{
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
                int resultRow = songDao.update(song);
                if (resultRow < 1){
                    opMsg = "更新用户失败！";
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
            resultEntity.setInfo_error("更新歌曲信息失败！");
            return resultEntity;
        }


    }

    @Override
    public ResultEntity get(Song song) {

        try{
            Boolean success = Boolean.FALSE;
            String errorMsg = "";//错误信息默认为空
            String opMsg="done";//操作信息默认为完成
            if (song.getId_Song()==null){
                errorMsg = "<ERROR> id_Song is NULL";
            } else {
                //根据请求参数查询歌曲信息
                song = songDao.queryByPK(song);

                if (song == null){
                    opMsg = "歌曲信息不存在";
                } else {
                    success = Boolean.TRUE;
                }
            }

            //封装返回结果
            ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, song);
            return resultEntity;
        }catch (Exception e){
            ResultEntity resultEntity = new ResultEntity();
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("获取歌曲信息失败！");
            return resultEntity;
        }

    }

    @Override
    public ResultEntity getSongInSongList(SongList songList) {
        ResultEntity resultEntity=new ResultEntity();

        try{
            if(songList.getId_SL()==null)
            {
                resultEntity.setInfo_error("<ERROR> id_SL is NULL");
                return resultEntity;
            }

            List<Song> list=songListDao.queryAllSongBySL(songList);

            for(Song item:list)
                item.setIconFile_Song(null);
            resultEntity.setState(true);

            return resultEntity;
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setState(false);
            resultEntity.setInfo_error("根据歌单获取歌曲信息失败！");
            return resultEntity;
        }

    }


}
