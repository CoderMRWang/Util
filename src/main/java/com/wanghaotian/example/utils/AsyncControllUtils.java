package com.wanghaotian.example.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author;Wanghaotian
 * data:2020/3/20 0020
 */
public class AsyncControllUtils {
    private final Async async = new Async();
    private int lockCount;
    private AtomicInteger lock;

    public AsyncControllUtils() {
        this.lockCount = 1;
        this.lock = new AtomicInteger(lockCount);
    }

    public AsyncControllUtils(int lockCount) {
        this.lockCount = lockCount;
        this.lock = new AtomicInteger(lockCount);

    }

    public boolean Accquire() {
        return async.rlock();
    }

    public int Release() {
        return async.runlock();
    }

    class Async {
        public boolean rlock() {
            int get= lock.get();
            if (get>=1)
            {
                lock.compareAndSet(get,get-1);
                return true;
            }else{
                return false;
            }
        }

        public int runlock() {
            return lock.incrementAndGet();
        }
    }
    public static void main(String[] args) {
        AsyncControllUtils asyncControllUtils = new AsyncControllUtils();
        for (int i = 0; i < 100; i++) {
            System.out.println(asyncControllUtils.Accquire());
            System.out.println(asyncControllUtils.Release());
        }
        AsyncControllUtils asyncControllUtils1=new AsyncControllUtils(5);
        for (int i=0;i<100;i++){
            System.out.println(asyncControllUtils1.Accquire());
        }

    }
}
