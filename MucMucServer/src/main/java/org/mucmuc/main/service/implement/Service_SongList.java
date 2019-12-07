package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.Interface_Map_SL_S_DAO;
import org.mucmuc.main.DAO.Interface_SongList_DAO;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_SL_S;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.Interface_SongList_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Service_SongList")
public class Service_SongList implements Interface_SongList_service {

    @Autowired
    private Interface_SongList_DAO songListDao;
    @Autowired
    private Interface_Map_SL_S_DAO map_S_SL_DAO;

    @Override
    public ResultEntity getRandom(Integer num) {
        ResultEntity resultEntity =new ResultEntity();

        if(num<=0||num>100)
        {
            resultEntity.setInfo_error("<ERROR> num must be in range[1,100]");
            return resultEntity;
        }

        resultEntity.setObject(songListDao.queryRandom(num));
        resultEntity.setState(true);
        return resultEntity;
    }

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
    public ResultEntity search(SongList songList) {
        return null;
    }

    @Override
    public ResultEntity getUserSongList(User user) {
        ResultEntity resultEntity=new ResultEntity();

        if(user.getId_User()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_User is NULL");
            return resultEntity;
        }
        resultEntity.setObject(songListDao.queryByUserID(user.getId_User()));
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity addSongToSongList(Song song, SongList songList) {
        ResultEntity resultEntity=new ResultEntity();

        if(song.getId_Song()==null||songList.getId_SL()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Song or id_SL is NULL");
            return resultEntity;
        }

        Map_SL_S map_SL_S=new Map_SL_S();
        map_SL_S.setId_Song(song.getId_Song());
        map_SL_S.setId_SL(songList.getId_SL());

        //从数据库中获取
        Map_SL_S map_SL_S_db=map_S_SL_DAO.queryByPK(map_SL_S);

        if(map_SL_S_db!=null)
        {
            resultEntity.setInfo_operation("歌单中已经包含该歌曲");
            return resultEntity;
        }
        //插入到数据库
        map_S_SL_DAO.insertNew(map_SL_S);
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity removeSongFromSongList(Song song, SongList songList) {
        ResultEntity resultEntity=new ResultEntity();

        if(song.getId_Song()==null||songList.getId_SL()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Song or id_SL is NULL");
            return resultEntity;
        }

        Map_SL_S map_SL_S=new Map_SL_S();
        map_SL_S.setId_Song(song.getId_Song());
        map_SL_S.setId_SL(songList.getId_SL());

        //从数据库中获取
        Map_SL_S map_SL_S_db=map_S_SL_DAO.queryByPK(map_SL_S);

        if(map_SL_S_db==null)
        {
            resultEntity.setInfo_operation("歌单中不存在该歌曲");
            return resultEntity;
        }
        //从数据库中删除
        map_S_SL_DAO.deleteByPK(map_SL_S);
        resultEntity.setState(true);
        return resultEntity;
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
