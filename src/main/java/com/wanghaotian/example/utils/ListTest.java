package com.wanghaotian.example.utils;

import com.wanghaotian.example.object.DelayObject;

import javax.management.AttributeList;
import javax.management.relation.Role;
import javax.management.relation.RoleList;
import javax.management.relation.RoleUnresolvedList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author;Wanghaotian
 * data:2020/4/17 0017
 */
public class ListTest {

    public static void main(String[] args) throws ParseException, InterruptedException {
        List list=new ArrayList();//动态数组
        List list1=new LinkedList();//链表
        Stack stack=new Stack();//栈
        List list3=new AttributeList();
        List list4=new CopyOnWriteArrayList();
        List list5=new RoleList();
        List list6=new RoleUnresolvedList();
//
//
//
//        Queue list7 =new LinkedBlockingDeque();//阻塞双向链表队列
//        Queue list8=new ArrayBlockingQueue(10);//阻塞动态数组队列
//        Queue list9=new ArrayDeque();//动态数组双向队列
//        Queue list10=new ConcurrentLinkedDeque();//线程安全双向链表队列
//        Queue list11=new ConcurrentLinkedQueue();//线程安全链表队列

        DelayQueue delayQueue=new DelayQueue();//延迟队列
        Date startDate=new Date();
        Date endDate=new Date();
        DelayObject delayObject=new DelayObject(startDate,endDate);
        delayQueue.offer(delayObject);
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(10);
        LinkedBlockingQueue linkedBlockingQueue=new LinkedBlockingQueue();
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition= reentrantLock.newCondition();
        condition.await();
        condition.signal();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate1=simpleDateFormat.parse("2020-04-17 17:55:20");
        Date endDate2=new Date();
        DelayObject delayObject1=new DelayObject(startDate1,endDate2);
        delayQueue.offer(delayObject1);
        System.out.println(delayQueue.poll());
        System.out.println(delayQueue.poll(1,TimeUnit.NANOSECONDS));

        Queue list14=new  LinkedBlockingQueue();//阻塞单项链表队列
        Queue list15=new LinkedList();//链表
        Queue list16=new LinkedTransferQueue();//
        Queue list17=new PriorityBlockingQueue();//
        Queue list18=new PriorityQueue();//
        Queue list19=new SynchronousQueue();//
        ArrayList arrayList=new ArrayList();
        for (int i=0;i<11;i++)
        {
            arrayList.add(1);
        }
        System.out.println(2>>1);

    }
}
