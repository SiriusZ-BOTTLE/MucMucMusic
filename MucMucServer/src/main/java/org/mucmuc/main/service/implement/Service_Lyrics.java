package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.implement.DAO_Lyrics;
import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.service.Interface_Lyrics_server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: mjj
 * @Date: Created in 20:52 2019/12/3
 * @Desprition: 歌词业务实现类
 */
@Service(value = "skrLyricsService")
public class Service_Lyrics implements Interface_Lyrics_server {

    @Autowired
    private org.mucmuc.main.DAO.implement.DAO_Lyrics DAO_Lyrics;


    @Override
    public ResultEntity getLyrics(RequestEntity requestEntity) {
        return null;
    }

    @Override
    public ResultEntity update(RequestEntity requestEntity) {
        return null;
    }

    @Override
    public ResultEntity delete(RequestEntity requestEntity) {
        return null;
    }

    @Override
    public ResultEntity add(RequestEntity requestEntity) {
        return null;
    }
}
