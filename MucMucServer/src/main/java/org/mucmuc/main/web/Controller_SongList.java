package org.mucmuc.main.web;

import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.service.implement.Service_SongList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 随机获取,获取指定数量的记录
     * @param num
     * @return
     */
    @RequestMapping(value = "/getRandom",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getRandom(@RequestBody Integer num)
    {
        return songListService.getRandom(num);
    }




    @RequestMapping(value = "/get",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity get(@RequestBody SongList songList)
    {
        return songListService.get(songList);
    }



    /**
     * 更新歌单信息
     * @param songList
     * @return
     */
    @RequestMapping(value = "/update",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(@RequestBody SongList songList)
    {
        return songListService.update(songList);
    }


    /**
     * 删除歌单
     * @param songList
     * @return
     */
    @RequestMapping(value = "/update",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody SongList songList)
    {
        return songListService.delete(songList);
    }


}
