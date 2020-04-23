package com.wanghaotian.example.test;

import org.apache.tomcat.util.threads.TaskQueue;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author;Wanghaotian
 * data:2020/3/29 0029
 */
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


        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,8,3, TimeUnit.SECONDS,
                new TaskQueue(10));
//        ConcurrentTaskExecutor concurrentTaskExecutor=new ConcurrentTaskExecutor(threadPoolExecutor);
//        concurrentTaskExecutor.submit(thread1);
//        concurrentTaskExecutor.submit(thread2);

        FutureTask<Boolean> futureTask=new FutureTask(thread1,true);
        threadPoolExecutor.execute(futureTask);
        threadPoolExecutor.execute(thread2);
        System.out.println(">>>"+futureTask.get());




//        Future<Boolean> future= concurrentTaskExecutor.submit(()->{
//            return false;
//        });
//        System.out.println(future.get());


//        System.out.println("执行数量:"+threadPoolExecutor.getActiveCount());

//        System.out.println("执行数量:"+threadPoolExecutor.getActiveCount());
    }
}
