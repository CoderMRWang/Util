package com.wanghaotian.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/12
 * @modify By:
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("服务器端返回结果:{}", NettyUtils.convertByteBufToString(byteBuf));
    }
}
