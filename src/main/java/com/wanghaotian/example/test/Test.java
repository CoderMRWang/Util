package com.wanghaotian.example.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskQueue;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

/**
 * author;Wanghaotian
 * data:2020/3/29 0029
 */
@Slf4j
public class Test {
    static Semaphore semaphore = new Semaphore(1);
    static Semaphore semaphoreb = new Semaphore(0);
    static AtomicInteger num = new AtomicInteger(0);
    static char[] bits = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'y', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, ClassNotFoundException, TimeoutException {

        Thread thread1 = new Thread(() -> {
            while (num.get() < 26) {
                try {
                    semaphore.acquire();
                    System.out.println(num.incrementAndGet());
                    semaphoreb.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (num.get() < 26) {
                try {
                    semaphoreb.acquire();
                    System.out.println(bits[num.get()-1]);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


//        ClassPathXmlApplicationContext patternResolver=new ClassPathXmlApplicationContext();
//        Resource[] resources=patternResolver.getResources("classpath:**/*.class");
//        MetadataReaderFactory metadataReaderFactory=new CachingMetadataReaderFactory();
//        for (Resource r:resources)
//        {
//            System.out.println("文件名:"+r.getFilename());
//            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(r);
//            String className = metadataReader.getClassMetadata().getClassName();
//            System.out.println("类名:"+r.getFilename());
//            Method[] methods= Class.forName(className).getDeclaredMethods();
//            for (Method method:methods)
//            {
//                System.out.println("方法名:"+method.getName());
//            }
//
//        }

        AtomicInteger atomicInteger=new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,20,3, TimeUnit.SECONDS,
                new TaskQueue(10));
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
            log.info("this is a good test!");
        });
        for (int i=0;i<15;i++){
            Thread thread=new Thread(()->{
                try {
                    log.info("{}",atomicInteger.getAndIncrement());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            threadPoolExecutor.execute(thread);
        }
        Phaser phaser=new Phaser();
//        CountDownLatch
        Semaphore semaphore=new Semaphore(5);
        semaphore.release(1);
        semaphore.acquire(3);
        log.info("1");
        CountDownLatch countDownLatch=new CountDownLatch(2);
        countDownLatch.countDown();

        phaser.arriveAndAwaitAdvance();

        StampedLock stampedLock=new StampedLock();
    }
}
