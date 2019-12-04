package org.mucmuc.main.service;

import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Lyrics;

public interface Interface_Lyrics_server {

    /**
     * 仅仅获得歌词(Lyrics)
     * @param lyrics
     * @return
     */
    ResultEntity getLyrics(Lyrics lyrics);


    /**
     * 修改歌词（管理员）(Lyrics)
     * @param lyrics
     * @return
     */
    ResultEntity update(Lyrics lyrics);

    /**
     * 删除歌词(管理员)(Lyrics)
     * @param lyrics
     * @return
     */
    ResultEntity delete(Lyrics lyrics);

    /**
     * 往歌曲中添加歌词(管理员)(Lyrics)
     * @param lyrics
     * @return
     */
    ResultEntity add(Lyrics lyrics);




}
