package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Map_SL_S;
import org.mucmuc.main.entity.Map_S_T;

import java.util.Map;

@Deprecated
public interface Interface_Map_S_T_service {

    /**
     * 仅仅一条映射
     * @param map_s_t
     * @return
     */
    ResultEntity getMap_S_T(Map_S_T map_s_t);


    /**
     * 仅仅所有映射
     * @return
     */
    ResultEntity getAll();


    /**
     * 删除歌曲的一条标签
     * @param map_s_t
     * @return
     */
    ResultEntity delete(Map_S_T map_s_t);

    /**
     * 往歌曲中添加标签
     * @param map_s_t
     * @return
     */
    ResultEntity add(Map_S_T map_s_t);
}
