package com.wanghaotian.example.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/8
 * @modify By:
 */
public class GuavaFutureDemo {
    static class HotWaterJob implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            return null;
        }
    }
    public static void main(String[] args) {
        ExecutorService jPool= Executors.newFixedThreadPool(10);
        ListeningExecutorService gPool= MoreExecutors.listeningDecorator(jPool);
        Callable<Boolean> hotWaterJob=new HotWaterJob();
        ListenableFuture<Boolean> hotFuture=gPool.submit(hotWaterJob);
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        },gPool);
    }
}
