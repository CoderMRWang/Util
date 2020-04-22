package com.wanghaotian.example.netty;

import io.netty.buffer.ByteBuf;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/11
 * @modify By:
 */
public class NettyUtils{
public static String convertByteBufToString(final ByteBuf buf) {
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
        str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
        }
}