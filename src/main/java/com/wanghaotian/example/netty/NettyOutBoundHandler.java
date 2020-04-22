package com.wanghaotian.example.netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/12
 * @modify By:
 */
@Slf4j
public class NettyOutBoundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
      log.info("{}","出站处理器调用");
    }

}
