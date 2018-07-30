package com.lanou.util;

import java.io.Serializable;

/**
 * Created by lanou on 2018/7/27.
 */
public class ServiceResponse<T> implements Serializable {

    private int errorcode;
    private String msg;
    private T data;

    public ServiceResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServiceResponse(int errorCode, String msg){
        this.errorcode = errorCode;
        this.msg = msg;
    }

    public static ServiceResponse createError(int errorCode, String msg){
        return new ServiceResponse(errorCode,msg);
    }

    public static ServiceResponse createError(int errorCode){
        return new ServiceResponse(errorCode,"操作失败");
    }

    private ServiceResponse(int errorcode, String msg, T data){
        this.errorcode=errorcode;
        this.msg=msg;
        this.data=data;
    }

    public static <T> ServiceResponse createSuccess(String msg, T data){
        return new ServiceResponse(0,msg,data);
    }
    public static <T> ServiceResponse createSuccess(String msg){
        return new ServiceResponse(0,msg);
    }
}
