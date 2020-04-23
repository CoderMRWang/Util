package com.wanghaotian.example.test;

/**
 * author;Wanghaotian
 * data:2020/4/19 0019
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedNode<Integer> linkedNode=new LinkedNode<>();
        linkedNode.addByHead(1);
        linkedNode.addByHead(2);
        linkedNode.addByHead(3);
        System.out.println(linkedNode);
        LinkedNode<Integer> linkedNode1=new LinkedNode<>();
        linkedNode1.addByFoot(1);
        linkedNode1.addByFoot(2);
        linkedNode1.addByFoot(3);
        System.out.println(linkedNode1);

    }
}
