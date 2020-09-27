package com.data.big.netty;


import com.data.big.util.CRCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        //创建字节数组,buffer.readableBytes可读字节长度
        int i = buffer.readerIndex();
        byte[] b = new byte[buffer.readableBytes()];
        //复制内容到字节数组b
        buffer.readBytes(b);
        // System.out.println(str);
        String s = bytesToHexString(b);
        String substring="";
        if(StringUtils.indexOf(s,"C0C0",s.indexOf("C0C0")+1)>0){
            s.substring(0,s.lastIndexOf("C0C0"));
            substring= s.substring(s.indexOf("C0C0"), s.indexOf("C0C0",1)+4);
            byte[] bytes = CRCUtil.hexToByteArray(substring);
            buffer.readerIndex(i+bytes.length);

        }else{
            buffer.readerIndex(i);
            return;
        }

        out.add(substring);
    }

    public String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }
}
