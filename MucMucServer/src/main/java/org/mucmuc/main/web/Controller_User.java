package org.mucmuc.main.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mucmuc.main.entity.User;
import org.mucmuc.main.service.implement.*;
import org.mucmuc.main.entity.InteractionEntity.*;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//控制器注解
@ResponseBody
@Controller
//@RestController//等价注解
@RequestMapping(value = "/user")
public class Controller_User {

    @Resource
    private Service_User userService;


    //@RequestBody需要把所有请求参数作为json解析，因此，不能包含key=value这样的写法在请求url
    //@RequestParam则是将接口函数的参数直接放在请求URL中,作为参数传递
    //如果使用PostMapping或者GetMapping注解, 则无需再加上"method=RequestMethod.POST"参数


    /**
     * 用户登录接口
     * @param user:要登录的用户信息(只需要账户和密码即可)
     * @return ResultEntity
     * 登录成功后用户对象会放入ResultEntity.object中
     */
    @RequestMapping(value = "/login",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity login(@RequestBody User user)
    {
        return userService.login(user);
    }

    /**
     * 用户注册接口
     * @param user:要注册的用户信息
     * @return ResultEntity
     */
    @RequestMapping(value = "/register",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity register(@RequestBody User user)
    {
        return userService.register(user);
    }

    /**
     * 获取用户接口
     * @param user
     * @return
     */

    @RequestMapping(value = "/get",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity get(@RequestBody User user)
    {
        return userService.get(user);
    }

    /**
     *
     * @param map
     * @return
     * 注意事项, 两个User对象必须命名为"user0","user1"
     */

    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity delete(@RequestBody Map<String,Object> map)
    {
        User user0,user1;
//        System.out.println(map.get("user0").getClass());
        user0=JSON.parseObject(JSON.toJSONString(map.get("user0")), User.class);
        user1=JSON.parseObject(JSON.toJSONString(map.get("user1")), User.class);

        return userService.delete(user0,user1);
    }

    @RequestMapping(value = "/update",method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public ResultEntity update(@RequestBody User user)
    {
        return userService.update(user);
    }




    //测试用接口
    //@RequestParam注解指示user是一个请求参数, 该注解也可以省略
    @PostMapping(value = "/test")
    public User test(@RequestParam User user0,@RequestParam User user1)
    {
        return user0;
    }







}
