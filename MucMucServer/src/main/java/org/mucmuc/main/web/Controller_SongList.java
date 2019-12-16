package org.mucmuc.main.web;

import com.alibaba.fastjson.JSON;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.SongList;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.implement.Service_SongList;
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
@RequestMapping(value = "/songList")
public class Controller_SongList {

    @Resource
    private Service_SongList songListService;


    /**
     * 搜索(按歌单名模糊搜索)
     * @param songList
     * @return
     */
    @RequestMapping(value = "/search",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity search(@RequestBody SongList songList)
    {
        return songListService.search(songList);
    }

    /**
     * 创建歌单(需要包含歌单名)
     * @param songList
     * @return
     */
    @RequestMapping(value = "/create",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity create(@RequestBody SongList songList)
    {
        return songListService.create(songList);
    }


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


    /**
     * 获取歌单信息(根据ID)
     * @param songList
     * @return
     */
    @RequestMapping(value = "/get",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity get(@RequestBody SongList songList)
    {
        return songListService.get(songList);
    }


    /**
     * 获得用户的全部歌单
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUserSongList",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getUserSongList(@RequestBody User user)
    {
        return songListService.getUserSongList(user);
    }



    /**
     * 添加歌曲至歌单
     * @param map
     * @return
     */
    @RequestMapping(value = "/addSongToSL",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity addSongToSongList(@RequestBody Map<String,Object> map)
    {
        Song song= JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        SongList songList=JSON.parseObject(JSON.toJSONString(map.get("songList")), SongList.class);


        return songListService.addSongToSongList(song,songList);
    }


    /**
     * 从歌单中删除歌曲
     * @param map
     * @return
     */
    @RequestMapping(value = "/removeSongFromSL",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity removeSongFromSongList(@RequestBody Map<String,Object> map)
    {
        Song song= JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        SongList songList=JSON.parseObject(JSON.toJSONString(map.get("songList")), SongList.class);

        return songListService.removeSongFromSongList(song,songList);
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
    @RequestMapping(value = "/delete",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody SongList songList)
    {
        return songListService.delete(songList);
    }


}
