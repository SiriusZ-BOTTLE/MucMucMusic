package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_SL_S;

@Deprecated
public interface Interface_Map_SL_S_service {

    /**
     * 获得一条映射
     * @param map_sl_s
     * @return
     */
    ResultEntity getMap_SL_S(Map_SL_S map_sl_s);

    /**
     * 获得所有映射
     * @return
     */
    ResultEntity getAll();

    /**
     * 删除歌单的歌曲
     * @param map_sl_s
     * @return
     */
    ResultEntity delete(Map_SL_S map_sl_s);

    /**
     * 往歌单添加歌曲
     * @param map_sl_s
     * @return
     */
    ResultEntity add(Map_SL_S map_sl_s);
}
