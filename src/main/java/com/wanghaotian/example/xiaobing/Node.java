package com.wanghaotian.example.xiaobing;


/**
 * author;Wanghaotian
 * data:2020/4/15 0015
 */
public class Node {
    private Node left;
    private boolean leftFinish;
    private Node right;
    private boolean rightFinish;
    private Node parent;
    private int num;
    public Node(int num){
        this.num=num;
    }

    public Node(Node left, boolean leftFinish, Node right, boolean rightFinish, Node parent, boolean searchAgo, int num) {
        this.left = left;
        this.leftFinish = leftFinish;
        this.right = right;
        this.rightFinish = rightFinish;
        this.parent = parent;
        this.num = num;
    }

    public void init(){
        this.leftFinish=false;
        this.rightFinish=false;
    }

    public int getNum() {
        return num;
    }

    public boolean isLeftFinish() {
        return leftFinish;
    }

    public void setLeftFinish(boolean leftFinish) {
        this.leftFinish = leftFinish;
    }

    public boolean isRightFinish() {
        return rightFinish;
    }

    public void setRightFinish(boolean rightFinish) {
        this.rightFinish = rightFinish;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "num:"+num;
    }
}
