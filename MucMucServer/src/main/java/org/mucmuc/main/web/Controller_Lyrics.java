package org.mucmuc.main.web;


import com.alibaba.fastjson.JSON;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Lyrics;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.service.implement.Service_Lyrics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//控制器注解
@ResponseBody
@Controller
//@RestController//等价注解
@RequestMapping(value = "/lyrics")
public class Controller_Lyrics {

    @Resource
    private Service_Lyrics lyricsService;

    //@RequestBody需要把所有请求参数作为json解析，因此，不能包含key=value这样的写法在请求url
    //@RequestParam则是将接口函数的参数直接放在请求URL中,作为参数传递
    //如果使用PostMapping或者GetMapping注解, 则无需再加上"method=RequestMethod.POST"参数


    /**
     * 获得歌词接口
     * @param lyrics
     * @return ResultEntity
     * 获得的歌词对象会放入ResultEntity.object中
     */
    @RequestMapping(value = "/getLyrics",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getLyrics(@RequestBody Lyrics lyrics)
    {
        return lyricsService.getLyrics(lyrics);
    }

    /**
     * 修改歌词接口
     * @param lyrics
     * @return ResultEntity
     */
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(@RequestBody Lyrics lyrics)
    {
        return lyricsService.getLyrics(lyrics);
    }

    /**
     * 删除歌词接口
     * @param lyrics
     * @return
     */

    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody Lyrics lyrics)
    {
        return lyricsService.delete(lyrics);
    }

    /**
     *往歌曲中添加歌词
     * @param lyrics
     * @return
     * 注意事项, 两个User对象必须命名为"user0","user1"
     */

    @RequestMapping(value = "/add",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity add(@RequestBody Lyrics lyrics)
    {
        return lyricsService.add(lyrics);
    }




}
