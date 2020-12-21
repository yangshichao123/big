package com.data.big.vo;

import lombok.Data;

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
@Data
public class Message {




    private  Boolean success;
    private  String status;
    private  Object data;
    private  Map<String,Object> dataMap =new HashMap<>();

    private  String message;
    private Timestamp timestamp;


    public Message ok(String msg, Object data) {
        this.setData(data);
        this.setStatus("0");
        this.setSuccess(Boolean.TRUE);
        this.setMessage(msg);
        this.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this;
    }
    public Message ok(String msg) {
        this.setData("");
        this.setStatus("0");
        this.setSuccess(Boolean.TRUE);
        this.setMessage(msg);
        this.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this;
    }
    public Message addData(String name,Object data) {
        dataMap.put(name,data);
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
