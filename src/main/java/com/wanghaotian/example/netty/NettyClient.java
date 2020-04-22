package com.wanghaotian.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/12
 * @modify By:
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        Bootstrap serverBootstrap=new Bootstrap();
        serverBootstrap.option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT );
        serverBootstrap.option(ChannelOption.TCP_NODELAY,true);
        serverBootstrap.remoteAddress("localhost",8090);
        serverBootstrap.channel(NioSocketChannel.class);
        EventLoopGroup parent=new NioEventLoopGroup();
        serverBootstrap.group(parent);
        serverBootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyClientHandler());
            }
        });
        ChannelFuture future =serverBootstrap.connect();
        future.addListener((l)->{
            if (l.isSuccess()){
                log.info("{}","连接成功!");
            }else{
                log.info("{}","连接失败!");
            }
        });
        future.sync();
        Channel channel=future.channel();
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext())
        {
            String next=scanner.next();
            ByteBuf byteBuf=channel.alloc().buffer();
            byteBuf.writeBytes(next.getBytes());
            channel.writeAndFlush(byteBuf);
        }
    }
}
