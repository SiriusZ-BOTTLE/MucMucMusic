package org.mucmuc.main.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mucmuc.main.entity.Comment;
import org.mucmuc.main.entity.InteractionEntity.RequestEntity;
import org.mucmuc.main.entity.InteractionEntity.ResultEntity;
import org.mucmuc.main.entity.Song;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.implement.Service_Comment;
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
@RequestMapping(value = "/comment")
public class Controller_Comment {

    @Resource
    private Service_Comment commentService;

    //@RequestBody需要把所有请求参数作为json解析，因此，不能包含key=value这样的写法在请求url
    //@RequestParam则是将接口函数的参数直接放在请求URL中,作为参数传递
    //如果使用PostMapping或者GetMapping注解, 则无需再加上"method=RequestMethod.POST"参数


    /**
     * 随机获取,获取指定数量的记录
     * @param num
     * @return
     */
    @RequestMapping(value = "/getRandom",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getRandom(@RequestBody Integer num)
    {
        return commentService.getRandom(num);
    }


    /**
     * 写评论接口
     * @param map:写评论所需信息comment user song
     * @return ResultEntity
     * 登录成功后用户对象会放入ResultEntity.object中
     * 不需传入日期, 自动设置
     */
    @RequestMapping(value = "/write",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity write(@RequestBody Map<String, Object> map)
    {
        Comment comment = JSON.parseObject(JSON.toJSONString(map.get("comment")), Comment.class);
        User user = JSON.parseObject(JSON.toJSONString(map.get("user")), User.class);
        Song song = JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);

        return commentService.write(comment,song,user);
    }

    /**
     * 写评论接口(和上面的接口功能一样,只不过是把信息全封装在comment里面了)
     * @param comment:写评论所需信息comment(包含歌曲ID,用户ID)
     * @return ResultEntity
     * 登录成功后用户对象会放入ResultEntity.object中
     */
    @RequestMapping(value = "/write2",method= RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity write2(@RequestBody Comment comment)
    {
        User user = new User();
        user.setId_User(comment.getId_User());
        Song song = new Song();
        song.setId_Song(comment.getId_Song());

        return commentService.write(comment,song,user);
    }




    /**
     * 更新()修改评论内容接口
     * @param comment
     * @return ResultEntity
     */
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(@RequestBody Comment comment)
    {
        return commentService.update(comment);
    }

    /**
     * 点赞接口
     * @param comment
     * @return
     */

    @RequestMapping(value = "/likes",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity likes(@RequestBody Comment comment)
    {
        return commentService.likes(comment);
    }

    /**
     * 踩接口
     * @param comment
     * @return
     */

    @RequestMapping(value = "/dislikes",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity dislikes(@RequestBody Comment comment)
    {
        return commentService.dislikes(comment);
    }


    /**
     * 修改评论score接口
     * @param comment
     * @return ResultEntity
     * 废弃了!!!!!!!不要使用
     */
    @Deprecated
    @RequestMapping(value = "/modifyscore",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity modifyscore(@RequestBody Comment comment)
    {
        return commentService.modifyscore(comment);
    }

    /**
     * 删除评论(管理员),更新歌曲score接口
     * @param comment
     * @return ResultEntity
     */
    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody Comment comment)
    {
        return commentService.delete(comment);
    }

    /**
     * 显示当前评论的子评论（Comment）接口
     * @param comment
     * @return
     */

    @RequestMapping(value = "/getReply",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getReply(@RequestBody Comment comment)
    {
        return commentService.queryByReply(comment);
    }

    /**
     * 获取当前歌曲的评论(song)
     * @param song 传入的歌曲对象
     * @return
     * 注意事项, 两个User对象必须命名为"user0","user1"
     */

    @RequestMapping(value = "/getCommentsUnderSong",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity getCommentsUnderSong(@RequestBody Song song)
    {
        return commentService.getCommentsUnderSong(song);
    }


    /**
     * 获得最新评论
     * @return
     */

    @RequestMapping(value = "/queryNew",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity queryNew()
    {
        return commentService.queryNew();
    }

}
