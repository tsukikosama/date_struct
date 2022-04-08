package com.miku.struct.linkLIst;

import com.miku.struct.linkLIst.LinklistNode;

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleLinklist linkList = new DoubleLinklist();

        LinklistNode2 node1 = new LinklistNode2(1,"aa","aa");
        LinklistNode2 node2 = new LinklistNode2(5,"bb","bb");
        LinklistNode2 node3 = new LinklistNode2(3,"cc","cc");
        LinklistNode2 node4 = new LinklistNode2(4,"dd","dd");

        linkList.addNode(node1);
        linkList.addNode(node2);
        linkList.addNode(node3);
        linkList.addNode(node4);

        linkList.printAllNodes();

        LinklistNode2 head = linkList.getHead();
        System.out.println(head);
        linkList.addByOrder(new LinklistNode2(2,"xxx","xxx"));
        linkList.printAllNodes();
    }


}
/**
 * 用于管理双链表的节点
 */
class DoubleLinklist{
    private LinklistNode2 head = new LinklistNode2(-1,"","") ;//定义一个头
    public LinklistNode2 getHead() {
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
        LinklistNode2 temp = head.next;
        boolean flag = false;
        while (true){
            //没找到
            if (temp == null){
                break;
            }
            //找到了
            if (temp.no == i){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //把这个节点的前一个节点指向这个节点的后一个节点
            temp.pre.next = temp.next;
            System.out.println("删除成功");
            //把这个节点的后一个节点的前指针 指向上一个节点//判断一下下一个是否为空 如果为空就不止
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }
    }
    /**
     * 修改节点
     * @param node
     */
    void updateNode(LinklistNode2 node){
        //这个直接获取下一个 否则判断为空时最后一个结点会直接跳过
        LinklistNode2 temp = head.next;
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
     *  添加节点  从尾巴添加
     */
    void addNode(LinklistNode2 node){
        //中间变量
        LinklistNode2 temp = head;

        while (true){
            //如果为空就是最后一个节点 然后把节点添加进去
            if (temp.next == null){
                break;
            }
            temp =  temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 便利所有的节点
     * @param
     */
    void  printAllNodes(){
        //中间变量
        LinklistNode2 temp = head.next;

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
    void addByOrder(LinklistNode2 node){
        //中间变量
        LinklistNode2 temp = head.next ;
        //判断是否重复的标记
        boolean flag = false;
        while (true){
            //没有下一个直接退出
            if (temp == null){
                break;
            }
            //下一个编号比这个大就找到了要插入的位置
            if (temp.no > node.no){

                break;
            }
            else if (temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("添加的编号已经存在请重新添加");
            return;
        }else {
            temp.pre.next = node;
            node.next = temp;
            node.pre = temp.pre;
            temp.pre =node;

        }


    }

}
class LinklistNode2{
    public int no;
    public String name;
    public String nickname;
    public LinklistNode2 next ;
    public LinklistNode2 pre ;
    LinklistNode2(){}


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

    public LinklistNode2 getNext() {
        return next;
    }

    public void setNext(LinklistNode2 next) {
        this.next = next;
    }

    LinklistNode2(int no , String name , String nickname){
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
