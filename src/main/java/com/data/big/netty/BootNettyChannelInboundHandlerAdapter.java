package com.data.big.netty;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.data.big.service.ServiceNetty;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * I/O数据读写处理类
 *
 */
public class BootNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter{

    private ServiceNetty serviceNetty;
    public static ChannelHandlerContext ctx;
    public static Boolean boo=false;
    private ByteBuf buf;

    public BootNettyChannelInboundHandlerAdapter() {
        serviceNetty=(ServiceNetty)nettyThread.applicationContext.getBean("ServiceNetty");
    }

    /**
     * 从服务端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException
    {
        serviceNetty.receiveData(ctx,msg);
        System.out.println("channelRead:read msg:"+msg.toString());
        //回应服务端

    }

    /**
     * 从服务端收到新的数据、读取完成时调用
     *
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException
    {
       // System.out.println("channelReadComplete");
        ctx.flush();
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws IOException
    {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();//抛出异常，断开与客户端的连接
    }

    /**
     * 客户端与服务端第一次建立连接时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        super.channelActive(ctx);
        BootNettyChannelInboundHandlerAdapter.ctx=ctx;
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        System.out.println("channelActive:"+clientIp+ctx.name());
        /*ByteBuf message = null;
        byte[] req = ("I am client once").getBytes();
        for(int i = 0; i < 5; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            Thread.sleep(5000);
            ctx.writeAndFlush(message);
        }
*/

        serviceNetty.sendRegister();
    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close(); //断开连接时，必须关闭，否则造成资源浪费
        ctx=null;
        BootNettyChannelInboundHandlerAdapter.ctx=null;
        BootNettyChannelInboundHandlerAdapter.boo=false;
        System.out.println("channelInactive:"+clientIp);
    }



}