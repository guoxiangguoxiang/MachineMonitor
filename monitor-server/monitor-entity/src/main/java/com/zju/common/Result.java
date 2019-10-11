package com.zju.common;

import java.io.Serializable;

/**
 * 返回体的基本构造
 * @param <T>
 */
public class Result<T> implements Serializable {

    protected static final long serialVersionUID = -4577255781088498763L;
    protected static final int OK = 0;
    protected static final int FAIL = 1;

    protected T data; //服务端数据
    private int status = OK; //状态码
    private String msg = ""; //描述信息

    //APIS
    public static Result isOk() {
        return new Result();
    }

    public static Result isFail() {
        return new Result().status(FAIL);
    }

    public static Result isFail(ServiceException e) {
        return isFail().msg(e);
    }
    public static Result isFail(ErrorStatusAndMessage e) {
        return isFail().msg(e);
    }

    public Result msg(ServiceException e) {
        this.setMsg(e.getMsg());
        this.setStatus(e.getStatus());
        return this;
    }
    public Result msg(ErrorStatusAndMessage e) {
        this.setMsg(e.msg);
        this.setStatus(e.status);
        return this;
    }
    public Result msg(String s) {
        this.setMsg(s);
        return this;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }

    public Result status(int status) {
        this.setStatus(status);
        return this;
    }

    //Constructors
    public Result() {

    }

   //Getter&Setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
