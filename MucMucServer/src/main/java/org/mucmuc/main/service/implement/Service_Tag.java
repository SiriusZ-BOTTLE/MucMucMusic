package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.implement.DAO_Map_S_T;
import org.mucmuc.main.DAO.implement.DAO_Tag;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_S_T;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.service.Interface_Tag_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Service_Tag")
public class Service_Tag implements Interface_Tag_service {

    @Autowired
    DAO_Tag tagDao;
    @Autowired
    DAO_Map_S_T map_S_T_Dao;

    @Override
    public ResultEntity getRandom(Integer num) {
        ResultEntity resultEntity =new ResultEntity();

        if(num<=0||num>100)
        {
            resultEntity.setInfo_error("<ERROR> num must be in range[1,100]");
            return resultEntity;
        }

        resultEntity.setObject(tagDao.queryRandom(num));
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity get(Tag tag) {
        ResultEntity resultEntity =new ResultEntity();

        if(tag.getId_Tag()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Tag is NULL");
            return resultEntity;
        }

        Tag tag_db=tagDao.queryByPK(tag);

        if(tag_db==null)
        {
            resultEntity.setInfo_operation("未找到指定标签");
            return resultEntity;
        }
        resultEntity.setObject(tag_db);
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity create(Song song, Tag tag) {
        ResultEntity resultEntity =new ResultEntity();
        if(song.getId_Song()==null||tag.getId_Tag()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Song or id_Tag is NULL");
            return resultEntity;
        }

        //按标签名查询
        Tag tag_db=tagDao.queryByName(tag);

        if(tag_db==null)//标签不存在
        {
            tagDao.insertNew(tag);//插入新的标签
            //重新查询标签(获取主键)
            tag_db=tagDao.queryByName(tag);
        }

        Map_S_T map_S_T=new Map_S_T();

        map_S_T.setNum(1);//第一次创建
        map_S_T.setId_Song(song.getId_Song());
        map_S_T.setId_Tag(tag_db.getId_Tag());
        //插入到数据库
        map_S_T_Dao.insertNew(map_S_T);
        resultEntity.setState(true);
        resultEntity.setObject(map_S_T);//返回创建的标签映射对象
        return resultEntity;
    }

    @Override
    public ResultEntity delete(Song song, Tag tag) {
        return null;
    }

    @Override
    public ResultEntity tagCountPlusOne(Song song, Tag tag) {

        ResultEntity resultEntity=new ResultEntity();

        if(song.getId_Song()==null||tag.getId_Tag()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Song or id_Tag is NULL");
            return resultEntity;
        }
        Map_S_T map_s_t=new Map_S_T();
        map_s_t.setId_Song(song.getId_Song());
        map_s_t.setId_Tag(tag.getId_Tag());
        //从数据库中查询
        Map_S_T map_s_t_db=map_S_T_Dao.queryByPK(map_s_t);
        if(map_s_t_db==null)
        {
            resultEntity.setInfo_operation("未找到歌曲指定的标签");
            return resultEntity;
        }
        map_s_t_db.setNum(map_s_t_db.getNum()+1);//该标签数自增1
        map_S_T_Dao.update(map_s_t_db);//更新数据库
        resultEntity.setState(true);
        return resultEntity;
    }

    @Override
    public ResultEntity tagCountMinusOne(Song song, Tag tag) {
        ResultEntity resultEntity=new ResultEntity();

        if(song.getId_Song()==null||tag.getId_Tag()==null)
        {
            resultEntity.setInfo_error("<ERROR> id_Song or id_Tag is NULL");
            return resultEntity;
        }
        Map_S_T map_s_t=new Map_S_T();
        map_s_t.setId_Song(song.getId_Song());
        map_s_t.setId_Tag(tag.getId_Tag());
        //从数据库中查询
        Map_S_T map_s_t_db=map_S_T_Dao.queryByPK(map_s_t);
        if(map_s_t_db==null)
        {
            resultEntity.setInfo_operation("未找到歌曲指定的标签");
            return resultEntity;
        }

        if(map_s_t_db.getNum()==0)
        {
            resultEntity.setInfo_operation("状态错误!标签数不可能为0,联系管理员");
            return resultEntity;
        }
        map_s_t_db.setNum(map_s_t_db.getNum()-1);//该标签数自减1

        if(map_s_t_db.getNum()==0)
            map_S_T_Dao.deleteByPK(map_s_t_db);//删除标签
        else
            map_S_T_Dao.update(map_s_t_db);//更新数据库
        resultEntity.setState(true);
        resultEntity.setState(true);
        return resultEntity;
    }
}
