package com.wanghaotian.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/11
 * @modify By:
 */
public class NettyDemo {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        EventLoopGroup boss=new NioEventLoopGroup(1);
        EventLoopGroup worker=new NioEventLoopGroup();
        serverBootstrap.group(boss,worker);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(8090);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyDiscardHandler()).addLast(new NettyOutBoundHandler());
            }
        });
        ChannelFuture channelFuture=serverBootstrap.bind().sync();
        channelFuture.channel().localAddress();
        ChannelFuture close=channelFuture.channel().closeFuture();
        close.sync();
        worker.shutdownGracefully();
        boss.shutdownGracefully();
    }
}
