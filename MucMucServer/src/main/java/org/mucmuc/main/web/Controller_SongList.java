package org.mucmuc.main.web;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.service.implement.Service_SongList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

//控制器注解
@ResponseBody
@Controller
//@RestController//等价注解
@RequestMapping(value = "/songList")
public class Controller_SongList {

    @Resource
    private Service_SongList songListService;

    /**
     * 更新歌单信息
     * @param songList
     * @return
     */
    @RequestMapping(value = "/update",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(SongList songList)
    {
        return songListService.update(songList);
    }


    /**
     * 删除歌单
     * @param songList
     * @return
     */
    @RequestMapping(value = "/update",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(SongList songList)
    {
        return songListService.delete(songList);
    }


}
