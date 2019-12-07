package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.Interface_Map_S_T_DAO;
import org.mucmuc.main.DAO.implement.DAO_Map_S_T;
import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.DAO.implement.DAO_Tag;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_S_T;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.service.Interface_Map_S_T_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 18:52 2019/12/5
 * @Desprition: 歌曲标签业务实现类
 */
@Service(value = "Service_Map_S_T")
public class Service_Map_S_T implements Interface_Map_S_T_service {


    @Override
    public ResultEntity getMap_S_T(Map_S_T map_s_t) {
        return null;
    }

    @Override
    public ResultEntity getAll() {
        return null;
    }

    @Override
    public ResultEntity delete(Map_S_T map_s_t) {
        return null;
    }

    @Override
    public ResultEntity add(Map_S_T map_s_t) {
        return null;
    }
}
