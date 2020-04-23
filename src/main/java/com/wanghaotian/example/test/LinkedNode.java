package com.wanghaotian.example.test;

/**
 * author;Wanghaotian
 * data:2020/4/19 0019
 */
public class LinkedNode<T> {
    private Node<T> head;
    private Node<T> current;
    private int size;

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    /**
     * 头插
     * */
    public void addByHead(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            size++;
        } else {
            Node<T> preHead = head;
            head = new Node<>(value, preHead);
            size++;
        }
    }
    /**
     * 尾插
     * */
    public void addByFoot(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            current = head;
            size++;
        } else {
            Node<T> tNode = new Node<>(value, null);
            current.next = tNode;
            current = tNode;
            size++;
        }
    }

    public static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private boolean hasNext(Node<T> node) {
        return node != null;
    }

    @Override
    public String toString() {
        String s = "[";
        Node<T> tNode = head;
        while (hasNext(tNode)) {
            s += tNode.value + ",";
            tNode = tNode.next;
        }
        s += "]";
        return s;
    }

}
