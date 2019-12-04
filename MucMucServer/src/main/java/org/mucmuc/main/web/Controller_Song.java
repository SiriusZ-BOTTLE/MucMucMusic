package org.mucmuc.main.web;


import com.alibaba.fastjson.JSON;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.service.implement.Service_Song;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

//控制器注解
@ResponseBody
@Controller
//@RestController//等价注解
@RequestMapping(value = "/user")
public class Controller_Song {

    @Resource
    private Service_Song songService;

    //@RequestBody需要把所有请求参数作为json解析，因此，不能包含key=value这样的写法在请求url
    //@RequestParam则是将接口函数的参数直接放在请求URL中,作为参数传递
    //如果使用PostMapping或者GetMapping注解, 则无需再加上"method=RequestMethod.POST"参数


    /**
     * 播放接口
     * @param
     * @return ResultEntity
     * 将歌曲url信息放入ResultEntity.object中
     */
    @RequestMapping(value = "/play",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity play(@RequestBody Song song)
    {
        return songService.play(song);
    }

    /**
     * 新增歌曲接口
     * @param song:
     * @return ResultEntity
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity add(@RequestBody Song song)
    {
        return songService.add(song);
    }

    /**
     * 删除歌曲接口
     * @param song
     * @return
     */

    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody Song song)
    {
        return songService.delete(song);
    }

    /**
     * 更新歌曲接口
     * @param song
     * @return
     */

    @RequestMapping(value = "/updateInfo",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity updateInfo(@RequestBody Song song)
    {
        return songService.updateInfo(song);
    }


    /**
     * 查询歌曲接口
     * @param song
     * @return ResultEntity
     */
    @RequestMapping(value = "/queryBySong",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity queryBySong(@RequestBody Song song)
    {
        return songService.queryBySong(song);
    }

    /**
     * 获取歌曲的所有标签
     * @param song
     * @return ResultEntity
     */
    @RequestMapping(value = "/queryTagBySong",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity queryTagBySong(@RequestBody Song song)
    {
        return songService.queryTagBySong(song);
    }


}