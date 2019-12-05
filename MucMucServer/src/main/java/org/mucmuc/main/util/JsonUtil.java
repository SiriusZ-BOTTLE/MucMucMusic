package org.mucmuc.main.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.mucmuc.main.entity.User;

import java.util.HashMap;
import java.util.Map;


//不建议使用, 建议直接使用JSON对象的方法
public class JsonUtil {

    public static String obj_to_json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public static <T> T json_to_obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json_to_map(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public static <T> T map_to_obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }


    //这是一个JSON使用示例
    //老马你可以看看
    public static void main(String []args)
    {
//        //映射,键值对
//        Map<String,Object> m=new HashMap<>();
//
//        User u0=new User();
//        User u1=new User();
//
//        u0.setId_User("u0");
//        m.put("u0",u0);//添加一个项
//
//        u1.setId_User("u1");
//        m.put("u1",u1);//添加一个项
//
//        String json=JSON.toJSONString(m);//转换为JSON字符串
//        System.out.println(json);//打印字符串
//
//        Map<String,Object> m_parsed;//解析字符串得到的map
//        m_parsed=JSON.parseObject(json,Map.class);//从刚才生成的字符串中解析键值对映射map
//
//        //注意, 解析之后任何对象都会被转换为JSONObject对象
//        System.out.println(m_parsed.get("u0").getClass());//这条语句打印其对象类型
//
//        JSONObject o=(JSONObject)(m_parsed.get("u0"));//获取这个JSONObject
//
//        User u=o.toJavaObject(User.class);//转换为Java的对象(这里是转换到User对象)
//
//        System.out.println(u.getId_User());//打印User对象的ID
        Integer i=5;
        String res=JSON.toJSONString(i);

        System.out.println(res);

    }


}
