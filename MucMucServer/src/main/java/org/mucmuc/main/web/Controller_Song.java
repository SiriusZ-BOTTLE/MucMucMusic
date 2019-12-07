package org.mucmuc.main.web;


import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.service.implement.Service_Song;
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
@RequestMapping(value = "/song")
public class Controller_Song {

    @Resource
    private Service_Song songService;

    //@RequestBody需要把所有请求参数作为json解析，因此，不能包含key=value这样的写法在请求url
    //@RequestParam则是将接口函数的参数直接放在请求URL中,作为参数传递
    //如果使用PostMapping或者GetMapping注解, 则无需再加上"method=RequestMethod.POST"参数

    /**
     * 搜索(按歌名模糊搜索)
     * @param song
     * @return
     */
    @RequestMapping(value = "/search",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity search(@RequestBody Song song)
    {
        return songService.search(song);
    }


    /**
     * 随机获取,获取指定数量的记录
     * @param num
     * @return
     */
    @RequestMapping(value = "/getRandom",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getRandom(@RequestBody int num)
    {
        return songService.getRandom(num);
    }



    /**
     * 获取歌曲信息
     * @param
     * @return ResultEntity
     * 将歌曲url信息放入ResultEntity.object中
     */
    @RequestMapping(value = "/get",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity get(@RequestBody Song song)
    {
        return songService.get(song);
    }

    /**
     * 新增歌曲
     * @param song:
     * @return ResultEntity
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity add(@RequestBody Song song)
    {
        return songService.add(song);
    }

    /**
     * 删除歌曲
     * @param song
     * @return
     */

    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody Song song)
    {
        return songService.delete(song);
    }

    /**
     * 更新歌曲
     * @param song
     * @return
     */

    @RequestMapping(value = "/update",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(@RequestBody Song song)
    {
        return songService.update(song);
    }


    /**
     * 查询歌曲
     * @param song
     * @return ResultEntity
     * 废弃了!!!!!!!!!不再使用
     */
    @Deprecated
    @RequestMapping(value = "/queryBySong",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity queryBySong(@RequestBody Song song)
    {
        return songService.get(song);
    }




}
