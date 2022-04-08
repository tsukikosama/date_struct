package com.miku.struct.linkLIst;

import java.util.Stack;

public class LinklistDemo {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();

        LinklistNode node1 = new LinklistNode(12,"cc","cc");
        LinklistNode node2 = new LinklistNode(24,"aa","aa");
        LinklistNode node3 = new LinklistNode(8,"vv","vv");
        LinklistNode node4 = new LinklistNode(4,"qq","qq");
        linkList.addByOrder(node1);
        linkList.addByOrder(node2);
        linkList.addByOrder(node4);
        linkList.addByOrder(node3);
//        System.out.println("反转前");
//        linkList.printAllNodes();
//        System.out.println("反转后");
//        reverseLinklist(linkList);
//        linkList.printAllNodes();
        //反转输出
        //reversePrint(linkList);
        LinkList linkList2 = new LinkList();

        LinklistNode node11 = new LinklistNode(9,"czzc","ccxx");
        LinklistNode node22 = new LinklistNode(7,"aa","aa");
        LinklistNode node33 = new LinklistNode(1,"vvs","vvs");
        LinklistNode node44 = new LinklistNode(2,"qq","qq");
        linkList2.addByOrder(node11);
        linkList2.addByOrder(node22);
        linkList2.addByOrder(node44);
        linkList2.addByOrder(node33);
        LinklistNode  wudi = sumLinkedList(linkList.getHead(),linkList2.getHead());
        LinkList n = new LinkList();
        n.addNode(wudi);
        n.printAllNodes();



    }

    /**
     * 两个有序链表和二为一

     * @return
     */
    public static LinklistNode sumLinkedList(LinklistNode head1,LinklistNode head2) {
        //判断是否为空
         if (head1 == null && head2 == null){
            return null;
        }
        if (head1 == null){
            return  head2;
        }
        if (head2 == null){
            return head1;
        }
        //定义一个节点用来接收数据
        LinklistNode newNode = new LinklistNode(0,"","");
        //创建一个头节点
        LinklistNode tail = newNode;

        newNode.setNext(tail);
        //获取传入过来的两个头节点
        LinklistNode temp1 = head1.getNext();
        LinklistNode temp2 = head2.getNext();
        //判断
        while (temp1 != null && temp2 != null){
            //判断哪个节点大
            if (temp1.no > temp2.no){
                // 暂时存储temp2的下一个节点
                LinklistNode next = temp2.getNext();
                // 将temp2的next设为null
                temp2.setNext(null);
                // 将temp2加入新的链表中
                tail.setNext(temp2);
                // tail变成了新增的节点temp2
                tail = temp2;
                // temp2后移
                temp2 = next;
            }else if (temp1.no < temp2.no){
                // 暂时存储temp1的下一个节点
                LinklistNode next = temp1.getNext();
                // 将temp1的next设为null
                temp1.setNext(null);
                // 将temp1加入到新的链表中
                tail.setNext(temp1);
                // tail变成了新增的节点temp1
                tail = temp1;
                // temp1后移
                temp1 = next;
            }else {
                LinklistNode next1 = temp1.getNext();
                LinklistNode next2 = temp2.getNext();
                tail.setNext(temp1);
                tail = temp1;
                temp1 = next1;
                temp2 = next2;
            }
        }




        return newNode;
    }

    /**
     * 反转输出单链表  利用栈的特性来实现
     */
    public static  void reversePrint(LinkList list){
        //获取头节点
        LinklistNode head = list.getHead();
        //判断是否为空
        if (head.next == null){
            System.out.println("为空");
        }
        //创建一个栈
        Stack<LinklistNode> stack = new Stack<>();
        LinklistNode temp = head.next;
        //遍历入栈
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        int index = stack.size();
        //遍历输出栈
        for(int i = 0; i < index ;i++){
            System.out.println(stack.pop());
        }


    }
    /**
     * 反转单链表
     * @param list
     */
    public static void reverseLinklist(LinkList list){
        LinklistNode head = list.getHead();
        //判断如果只有一个或者为空 直接返回 不需要反转
        if (head.next == null || head.next.next == null){
            System.out.println("不需要反转");
            return;
        }
        //中间变量
        LinklistNode nextP = null;
        //定义一个临时的变量
        LinklistNode temp = head.next;
        //获取到反转链表的头
        LinklistNode reverseHead = new LinklistNode(-1,"","");
        while (temp != null){
            //先定位到下一个 防止之后链表断了
            nextP = temp.next ;
            //把反转链表里面的东西 和 当前的节点连起来
            temp.next = reverseHead.next;
            //把添加的节点重新添加到反转链表中
            reverseHead.next = temp;
            //移动到下一个
            temp = nextP;
        }
        //指向反转之后的链表
        head.next = reverseHead.next;
    }
    /**
     * 返回倒数第n个节点
     * @param list
     * @param index
     * @return
     */
    public static LinklistNode getLastNode(LinkList list,int index){
        //判断节点是否为空
        LinklistNode temp = list.getHead().next;
        if (temp.next == null){
            System.out.println("节点为空");
            return null;
        }
        if (index <0 || index >getLength(list)){
            System.out.println("越界");
            return null;
        }
        for (int i = 0 ; i < getLength(list) - index ; i++){
            temp = temp.next;
        }


        return temp;
    }

    /**
     *  获取链表有效节点个数
     */

    public static int getLength(LinkList list){
        //计数
        int length = 0;
        LinklistNode temp = list.getHead();
        while (true) {
            //判断链表是否为空
            if (temp.next == null) {
                break;
            } else {
                length++;
            }
            temp=temp.next;
        }
        return length;
    }
}

//单链表
class LinkList{


    public LinklistNode getHead() {
        return head;
    }

    /**
     * 获取最后一个节点
     * @param node
     * @return
     */
    public LinklistNode getLastNode(LinklistNode node){
        while (node != null){
            if (node.next == null) {
                return node;
            } else {
                node = node.next;
            }
        }
        return null;
    }
    /**
     * 删除链表
     */
    void deleteNode(int i){
        LinklistNode temp = head;
        boolean flag = false;
        while (true){
            //没找到
            if (temp.next == null){
                break;
            }
            //找到了
            if (temp.next.no == i ){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
            System.out.println("删除成功");
        }
    }

    /**
     * 修改节点
     * @param node
     */
    void updateNode(LinklistNode node){
        //这个直接获取下一个 否则判断为空时最后一个结点会直接跳过
        LinklistNode temp = head.next;
        boolean flag = false;

        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = node.name;
            temp.nickname = node.nickname;
        }else {
            System.out.println("没有找到该节点");
        }
    }

    /**
     * 初始化头节点
     * 因为头节点不能动 所以需要一个临时变量
     * @return
     */
    private LinklistNode head = new LinklistNode(-1,"","") ;
    /**
     *  添加节点
     */
    void addNode(LinklistNode node){
        //中间变量
        LinklistNode temp = head;

        while (true){
            //如果为空就是最后一个节点 然后把节点添加进去
            if (temp.next == null){
                break;
            }
            temp =  temp.next;
        }
        temp.next = node;
    }

    /**
     * 便利所有的节点
     * @param
     */
    void  printAllNodes(){
        //中间变量
        LinklistNode temp = head.next;

        while (true){
            //判断下一个是否为空  不为空就输出 然后移到下一个节点 为空直接结束
            if (temp != null){
                System.out.println(temp);
                temp = temp.next;
            }else {
                break;
            }

        }
    }

    /**
     * 按照编号顺序去添加节点
     * @param node
     */
    void addByOrder(LinklistNode node){
        //中间变量
        LinklistNode temp = head ;
        //判断是否重复的标记
        boolean flag = false;
        while (true){
            //没有下一个直接退出
            if (temp.next == null){
                break;
            }
            //下一个编号比这个大就找到了要插入的位置
            if (temp.next.no > node.no){
                break;
            }
            else if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("添加的编号已经存在请重新添加");
            return;
        }else {
            node.next = temp.next;
            temp.next = node;
        }

    }

}


class LinklistNode{
    public int no;
    public String name;
    public String nickname;
    public  LinklistNode next ;
    LinklistNode(){}


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LinklistNode getNext() {
        return next;
    }

    public void setNext(LinklistNode next) {
        this.next = next;
    }

    LinklistNode(int no , String name , String nickname){
        this.no = no ;
        this.name = name ;
        this.nickname = nickname;
    }
    //重写toString方便打印


    @Override
    public String toString() {
        return "Linklist{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }


}
