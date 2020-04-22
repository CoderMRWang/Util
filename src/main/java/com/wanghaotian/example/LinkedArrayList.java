package com.wanghaotian.example;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/6
 * @modify By:
 */
public class LinkedArrayList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modcount;
    public boolean Add(T item){
       final Node<T> newNode=new Node<>(item);
       final Node<T> lastNode=tail;
        if (tail==null){
            head=newNode;
            tail=newNode;
            size++;
            modcount++;
        }else{
            tail=newNode;
            lastNode.next=newNode;
            newNode.pre=lastNode;
        }


        return false;
    }

    private class Node<T>{
        private Node pre;
        private Node next;
        private T item;

        public Node(T item) {
            this.item = item;
        }
    }
}
