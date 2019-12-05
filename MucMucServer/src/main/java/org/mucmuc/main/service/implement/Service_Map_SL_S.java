package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.Interface_Map_SL_S_DAO;
import org.mucmuc.main.DAO.implement.DAO_Map_SL_S;
import org.mucmuc.main.entity.Map_SL_S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 18:52 2019/12/5
 * @Desprition: 歌单中歌曲业务实现类
 */
@Service(value = "Service_Map_SL_S")
public class Service_Map_SL_S implements Interface_Map_SL_S_DAO {

    @Autowired
    private DAO_Map_SL_S dao_map_sl_s;

    @Override
    public Map_SL_S queryByPK(Map_SL_S map_sl_s) {
        return null;
    }

    @Override
    public List<Map_SL_S> queryAll() {
        return null;
    }

    @Override
    public int deleteByPK(Map_SL_S map_sl_s) {
        return 0;
    }

    @Override
    public int insertNew(Map_SL_S map_sl_s) {
        return 0;
    }
}
