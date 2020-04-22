package com.wanghaotian.example.nio;

import com.wanghaotian.example.LinkedArrayList;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/6
 * @modify By:
 * acceptor
 */
@Slf4j
public class NIODemo {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        Predicate<String> p=(s ->s.equals(1));
        System.out.println(list.stream().allMatch(p));
        try {
            ServerSocketChannel sc=ServerSocketChannel.open();
            sc.configureBlocking(false);
            SocketAddress address=new InetSocketAddress(8082);
            sc.bind(address);
            Selector selector = Selector.open();
            SelectionKey selectionKey= sc.register(selector, SelectionKey.OP_ACCEPT);
            while(selector.select()>0){
            Iterator<SelectionKey>  iterator=selector.selectedKeys().iterator();
            while(iterator.hasNext())
            {
                SelectionKey key =  iterator.next();
                if (key.isAcceptable())
                {

                }else if (key.isConnectable()){

                }else if(key.isReadable()){

                }else if(key.isValid()){

                }else if (key.isWritable()){

                }
            }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
