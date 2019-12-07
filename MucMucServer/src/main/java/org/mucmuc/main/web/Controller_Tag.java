package org.mucmuc.main.web;

import com.alibaba.fastjson.JSON;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.Tag;
import org.mucmuc.main.service.implement.Service_Tag;
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
@RequestMapping(value = "/tag")
public class Controller_Tag {

    @Resource
    private Service_Tag tagService;



    /**
     * 搜索(按歌名模糊搜索)
     * @param tag
     * @return
     */
    @RequestMapping(value = "/search",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity search(@RequestBody Tag tag)
    {
        return tagService.search(tag);
    }





    /**
     * 获取指定数量的记录
     * @param num
     * @return
     * Object:List<Tag>
     */
    @RequestMapping(value = "/getRandom",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getRandom(@RequestBody Integer num)
    {
        return tagService.getRandom(num);
    }

    /**
     * 获取标签对象
     * @param tag
     * @return
     * Object:返回的标签对象
     */
    @RequestMapping(value = "/get",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity get(@RequestBody Tag tag)
    {
        return tagService.get(tag);
    }


    /**
     * 创建一个歌曲下的标签(映射)
     * 传入Map中歌曲参数名为song,标签参数名为tag
     * @param map
     * @return
     * Object:返回的映射对象
     */
    @RequestMapping(value = "/create",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity create(Map<String,Object> map)
    {
        Song song=JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        Tag tag=JSON.parseObject(JSON.toJSONString(map.get("tag")), Tag.class);
        return tagService.create(song,tag);
    }

    /**
     * 歌曲标签计数+1
     * 传入Map中歌曲参数名为song,标签参数名为tag
     * @param map
     * @return
     * Object:null
     */
    @RequestMapping(value = "/tagCountPlusOne",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity tagCountPlusOne(Map<String,Object> map)
    {
        Song song=JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        Tag tag=JSON.parseObject(JSON.toJSONString(map.get("tag")), Tag.class);
        return tagService.tagCountPlusOne(song,tag);
    }

    /**
     * 歌曲标签计数-1
     * 传入Map中歌曲参数名为song,标签参数名为tag
     * @param map
     * @return
     * Object:null
     */
    @RequestMapping(value = "/tagCountMinusOne",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity tagCountMinusOne(Map<String,Object> map)
    {
        Song song=JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        Tag tag=JSON.parseObject(JSON.toJSONString(map.get("tag")), Tag.class);
        return tagService.tagCountMinusOne(song,tag);
    }


    /**
     * 获取歌曲的所有标签
     * @param song
     * @return ResultEntity
     */
    @RequestMapping(value = "/getTagBySong",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getTagBySong(@RequestBody Song song)
    {
        return tagService.getTagBySong(song);
    }


}
