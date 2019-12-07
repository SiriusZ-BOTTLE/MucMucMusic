package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.Interface_Map_SL_S_DAO;
import org.mucmuc.main.DAO.implement.DAO_Map_SL_S;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_SL_S;
import org.mucmuc.main.service.Interface_Map_SL_S_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 18:52 2019/12/5
 * @Desprition: 歌单中歌曲业务实现类
 */
@Service(value = "Service_Map_SL_S")
public class Service_Map_SL_S implements Interface_Map_SL_S_service {

    @Autowired
    private DAO_Map_SL_S dao_map_sl_s;


    @Override
    public ResultEntity getMap_SL_S(Map_SL_S map_sl_s) {
        return null;
    }

    @Override
    public ResultEntity getAll() {
        return null;
    }

    @Override
    public ResultEntity delete(Map_SL_S map_sl_s) {
        return null;
    }

    @Override
    public ResultEntity add(Map_SL_S map_sl_s) {
        return null;
    }
}
