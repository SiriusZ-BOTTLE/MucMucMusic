package org.mucmuc.main.service.implement;


import org.mucmuc.main.DAO.Interface_Map_S_T_DAO;
import org.mucmuc.main.DAO.implement.DAO_Map_S_T;
import org.mucmuc.main.DAO.implement.DAO_Song;
import org.mucmuc.main.DAO.implement.DAO_Tag;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_S_T;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mjj
 * @Date: Created in 18:52 2019/12/5
 * @Desprition: 歌曲标签业务实现类
 */
@Service(value = "Service_Map_S_T")
public class Service_Map_S_T implements Interface_Map_S_T_DAO {

    @Autowired
    private DAO_Map_S_T dao_map_s_t;

    @Autowired
    private DAO_Song dao_song;

    @Autowired
    private DAO_Tag dao_tag;

    @Override
    public Map_S_T queryByPK(Map_S_T map_s_t) {
        Boolean success = Boolean.FALSE;
        String errorMsg = "";//错误信息默认为空
        String opMsg="done";//操作信息默认为完成
        Tag tag = new Tag();tag.setId_Tag(map_s_t.getId_Tag());
        Song song = new Song();song.setId_Song(map_s_t.getId_Song());
        tag = dao_tag.queryByPK(tag);
        Song songList = dao_song.queryByPK(song);
        if (map_s_t == null){
            errorMsg = "请求数据不能为空！";
        } else {
            //根据请求参数获得映射
            map_s_t = dao_map_s_t.queryByPK(map_s_t);
            if (map_s_t == null){
                errorMsg = "此标签映射信息不存在！";
            }else if(song == null){
                errorMsg = "没有此歌曲，错误";
            } else if(tag == null){
                errorMsg = "没有此标签，错误";
            }else {
                success = Boolean.TRUE;
            }
        }

        //封装返回结果
        ResultEntity resultEntity = new ResultEntity(success,errorMsg, opMsg, map_s_t);
        return resultEntity;
    }

    @Override
    public List<Map_S_T> queryAll() {
        return null;
    }

    @Override
    public int deleteByPK(Map_S_T map_s_t) {
        return 0;
    }

    @Override
    public int update(Map_S_T map_s_t) {
        return 0;
    }

    @Override
    public int insertNew(Map_S_T map_s_t) {
        return 0;
    }
}
