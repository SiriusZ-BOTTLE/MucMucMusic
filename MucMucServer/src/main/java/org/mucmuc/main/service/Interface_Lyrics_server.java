package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;

public interface Interface_Lyrics_server {

    /**
     * 仅仅获得歌词(song)
     * @param requestEntity
     * @return
     */
    ResultEntity getLyrics(RequestEntity requestEntity);


    /**
     * 修改歌词（管理员）(Lyrics)
     * @param requestEntity
     * @return
     */
    ResultEntity update(RequestEntity requestEntity);

    /**
     * 删除歌词(管理员)(song)
     * @param requestEntity
     * @return
     */
    ResultEntity delete(RequestEntity requestEntity);

    /**
     * 往歌曲中添加歌词(管理员)(song)
     * @param requestEntity
     * @return
     */
    ResultEntity add(RequestEntity requestEntity);




}
