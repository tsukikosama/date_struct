package com.miku.struct.hafumanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  创建哈夫曼树
 */
public class HafumanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node hTree = createHTree(arr);
        preOrder(hTree);

    }
    /**
     * 先序遍历
     */
    public static  void preOrder(Node node){
        if (node == null){
            System.out.println("树为空");
        }
        node.preOrder(node);
    }
    /**
     * 创建一个哈夫曼树
     */
    public static Node createHTree(int[] arr){
        //用来存储数据
        List<Node> list = new ArrayList<>();
        //把这个数组变成一个list集合
        for (int k : arr){
            list.add(new Node(k));
        }
        //给这个数组排序
        Collections.sort(list);
        System.out.println(list);
        //循环取出最小的两个形成一个新的树
        while (list.size()>1){
            //取出最小的
            Node leftNode = list.get(0);
            //取出第二小的
            Node rightNode = list.get(1);
            //形成一个新的节点去连接这两个数
            Node parentNode = new Node(rightNode.value+leftNode.value);
            //父节点连接上取出的两个节点 设定左指数小于右子树
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            //然后把取出来的两个节点 从list集合中移除
            list.remove(leftNode);
            list.remove(rightNode);
            //把新生成的这个节点 添加进list集合总
            list.add(parentNode);
            //重新开始排序 为了下一次的最小生成树
            Collections.sort(list);
        }
        return list.get(0);
    }
}




class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;


    /**
     * 前序输出树
     * @return
     */
    public void preOrder(Node node){
        if (node == null){

            return;
        }
        System.out.println(this.value);
        //判断左指数是不是为空
        if (node.left != null){
            this.left.preOrder(this.left);
        }
        //判断右指数是不是为空
        if (node.right != null){
            this.right.preOrder(this.right);
        }
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

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}