package com.data.big.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 * {
 *   meta:{"code":code,“msg”:message}
 *   data:{....}
 * }
 * @author tomsun28
 * @date 10:48 2018/2/14
 */
public class Message {




    private  Boolean success;
    private  String status;
    private  Object data;
    private  String message;
    private Timestamp timestamp;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Message ok(String msg, Object data) {
        this.setData(data);
        this.setStatus("0");
        this.setSuccess(Boolean.TRUE);
        this.setMessage(msg);
        this.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this;
    }
    public Message error( String msg) {
        this.setStatus("1");
        this.setSuccess(Boolean.FALSE);
        this.setMessage(msg);
        this.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this;
    }
}
