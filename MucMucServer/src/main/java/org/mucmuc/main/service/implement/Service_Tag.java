package org.mucmuc.main.service.implement;

import org.mucmuc.main.DAO.implement.DAO_Tag;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.service.Interface_Tag_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Service_Tag")
public class Service_Tag implements Interface_Tag_service {

    @Autowired
    DAO_Tag tagDao;

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
        return null;
    }

    @Override
    public ResultEntity create(Song song, Tag tag) {
        return null;
    }

    @Override
    public ResultEntity delete(Song song, Tag tag) {
        return null;
    }

    @Override
    public ResultEntity tagCountPlusOne(Song song, Tag tag) {
        return null;
    }

    @Override
    public ResultEntity tagCountMinusOne(Song song, Tag tag) {
        return null;
    }
}
