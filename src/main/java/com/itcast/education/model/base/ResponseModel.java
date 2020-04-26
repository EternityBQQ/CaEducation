package com.itcast.education.model.base;

/**
 * 结果集返回响应状态码
 * @author zheng.zhang
 */
public class ResponseModel {
    /**
     * 响应业务状态
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应中的数据
     */
    private Object responseData;

    public ResponseModel() {}

    /**
     * 可以手动设置消息和状态码
     * @param status
     * @param msg
     * @param responseData
     */
    public ResponseModel(Integer status, String msg, Object responseData) {
        this.status = status;
        this.msg = msg;
        this.responseData = responseData;
    }

    /**
     * 返回数据对象
     * @param responseData
     */
    public ResponseModel(Object responseData) {
        this.status = 0;
        this.msg = "OK";
        this.responseData = responseData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public static ResponseModel ok() {
        return new ResponseModel(null);
    }

    public static ResponseModel ok(Object data) {
        return new ResponseModel(data);
    }

    public static ResponseModel build(Integer status, String msg) {
        return new ResponseModel(status, msg, null);
    }

    public static ResponseModel build(Integer status, String msg, Object data) {
        return new ResponseModel(status, msg, data);
    }
}
