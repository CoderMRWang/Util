package com.wanghaotian.example.xiaobing;

import java.util.ArrayList;
import java.util.List;

/**
 * author;Wanghaotian
 * data:2020/4/15 0015
 */
public class Xiaobing {
    /**
     * 翻转字符
     * 每次将第i个字符与length-1-i交换，执行length/2次
     */

    public static void reverString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            char tmp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = tmp;
        }
        System.out.println(s);
    }

    /**
     * 二叉树
     */

    public static synchronized void binaryTree(Node root, Node first, Node second, List<Node> nodes) throws InterruptedException, CloneNotSupportedException {
        List<Node> fParentList1 = new ArrayList<>();
        List<Node> sParentList2 = new ArrayList<>();
        search(root, first, fParentList1);
        for (int i=0;i<nodes.size();i++)
        {
            nodes.get(i).init();
        }
        search(root, second, sParentList2);
        Node firstParent;
        if (sParentList2.size() > fParentList1.size()) {
            firstParent = findFirstParent(fParentList1, sParentList2);
        } else {
            firstParent = findFirstParent(fParentList1, sParentList2);
        }
        System.out.println(firstParent);
    }

    /**
     * 深度优先查找node和查找node节点所有parent节点
     */
    public  static void search(Node tNode, Node node, List<Node> parentList) {
        Node left = tNode.getLeft();
        Node right = tNode.getRight();
        if (left != null && !left.isLeftFinish()) {
            left.setParent(tNode);
            if (node.equals(left)) {
                left.setLeftFinish(true);
                findParent(left, parentList);
            } else {
                left.setLeftFinish(true);
                search(left, node, parentList);
            }
        } else if (right != null && !right.isRightFinish()) {
            right.setParent(tNode);
            if (node.equals(right)) {
                right.setRightFinish(true);
                findParent(right, parentList);
            } else {
                right.setRightFinish(true);
                search(right, node, parentList);
            }
        } else {
            if (tNode.getParent() != null) {
                search(tNode.getParent(), node, parentList);
            }

        }

        if (node.isRightFinish() && node.isLeftFinish()) {
            if (node.getParent() != null) {
                search(node.getParent(), node, parentList);
            }
        }
    }

    /**
     * 查找最近的父节点
     */
    public  static Node findFirstParent(List<Node> longParentList, List<Node> smallParentList) {
        Node result = null;
        for (Node node : smallParentList) {
            if (longParentList.contains(node)) {
                result = node;
            }
        }
        return result;
    }
    /**
     * 查找parent节点具体逻辑
     **/
    public static List<Node> findParent(Node node, List<Node> parentList) {
        Node p;
        parentList.add(node);
        while ((p = node.getParent()) != null) {
            parentList.add(p);
            node = p.getParent();
        }
        System.out.println(parentList);
        return parentList;
    }
    public static void main(String[] args) {
        char[] bits = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'y', 'u', 'v', 'w', 'x', 'y', 'z'};
        reverString(bits);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        try {
            binaryTree(node1, node7, node6,nodeList);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
