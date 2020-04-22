package com.wanghaotian.example.netty;

import io.netty.buffer.*;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/11
 * @modify By:
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        while (byteBuf.isReadable()) {
            log.info("{}", NettyUtils.convertByteBufToString(byteBuf));
            ByteBuf result= ByteBufAllocator.DEFAULT.buffer();
            ByteBufUtil.writeUtf8(result,"服务端UTF8返回!");
            ChannelFuture channelFuture = ctx.writeAndFlush(result);
            channelFuture.addListener((l) -> {
                if (l.isSuccess()) {
                    log.info("返回发送成功!");
                } else {
                    log.info("返回发送失败!");
                }
            });
            byteBuf.clear();
        }

    }
}
