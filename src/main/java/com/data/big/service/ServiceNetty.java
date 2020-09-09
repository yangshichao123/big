package com.data.big.service;

import io.netty.channel.ChannelHandlerContext;

public interface ServiceNetty {
    /**
     * 接收数据接口
     * @param ctx
     * @param msg
     */
    void receiveData(ChannelHandlerContext ctx, Object msg);

    /**
     * 发送心跳
     */
    void sendKeepAlive();

    /**
     * 发送注册
     */
    Boolean sendRegister();


    void addTask();
}
