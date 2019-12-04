package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.Interface_SongList_DAO;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.service.Interface_SongList_server;
import org.springframework.beans.factory.annotation.Autowired;

public class Service_SongList implements Interface_SongList_server {

    @Autowired
    private Interface_SongList_DAO songListDao;

    @Override
    public ResultEntity get(SongList songList) {
        ResultEntity resultEntity=new ResultEntity();

        if(songList.getId_SL()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_SL is NULL");
            return resultEntity;
        }

        //数据库中获取
        SongList songList_db=songListDao.queryByPK(songList);

        if(songList_db==null)
        {
            resultEntity.setInfo_operation("歌单不存在");
            return resultEntity;
        }

        resultEntity.setState(true);
        resultEntity.setObject(songList_db);

        return resultEntity;
    }

    @Override
    public ResultEntity getByAttribute(SongList songList) {
        return null;
    }

    @Override
    public ResultEntity update(SongList songList) {

        ResultEntity resultEntity=new ResultEntity();
        if(songList.getId_SL()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_SL is NULL");
            return resultEntity;
        }

        //数据库中获取
        SongList songList_db=songListDao.queryByPK(songList);

        if(songList_db==null)
        {
            resultEntity.setInfo_operation("歌单不存在");
            return resultEntity;
        }

        songListDao.update(songList);
        //再次获取一遍
        songList_db=songListDao.queryByPK(songList);

        resultEntity.setObject(songList_db);
        resultEntity.setState(true);

        return resultEntity;
    }

    @Override
    public ResultEntity delete(SongList songList) {
        ResultEntity resultEntity=new ResultEntity();

        if(songList.getId_SL()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_SL is NULL");
            return resultEntity;
        }

        SongList songList_db=songListDao.queryByPK(songList);

        if(songList_db==null)
        {
            resultEntity.setInfo_operation("歌单不存在");
            return resultEntity;
        }
        //删除
        songListDao.deleteByPK(songList);
        resultEntity.setState(true);
        return resultEntity;
    }
}
