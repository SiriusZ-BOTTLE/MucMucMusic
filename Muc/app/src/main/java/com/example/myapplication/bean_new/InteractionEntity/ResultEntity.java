package com.example.myapplication.bean_new.InteractionEntity;

import java.io.Serializable;


/**
 * @Author ZSR
 * 注:
 * state字段指示总体操作是否成功完成, 无论发生了前端调用错误,或者逻辑上无法完成操作(比如用户登录时账号不存在的情况)
 * state字段都应该为false, 仅当
 */


//后台响应结果封装实体类
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 3401781428101852075L;

    //状态信息,true表示成功,false表示失败
    private Boolean state=false;
    //错误信息(指示前端的调用错误)
    private String info_error="";
    //操作信息(执行操作的返回信息)
    private String info_operation="Undefined";
    //返回对象
    private Object object=null;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getInfo_error() {
        return info_error;
    }

    public void setInfo_error(String info_error) {
        this.info_error = info_error;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getInfo_operation() {
        return info_operation;
    }

    public void setInfo_operation(String info_operation) {
        this.info_operation = info_operation;
    }

    /**
     *
     * @param state
     * @param info_error
     * @param object
     */
    public ResultEntity(Boolean state, String info_error, Object object) {
        this.state = state;
        this.info_error = info_error;
        this.object = object;
    }

    public ResultEntity() {

    }

}
