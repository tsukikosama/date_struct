package com.miku.struct;

import java.util.Scanner;

public class circleQueue {
    public static void main(String[] args) {
        //队列测试
        boolean flag = true ;
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue(4);
        while (flag){

            System.out.println("1 添加数据");
            System.out.println("2 删除数据");
            System.out.println("3 显示数据");
            System.out.println("4 显示对头");
            int k = scanner.nextInt();

                    try {
                        switch (k){
                            case 1:
                        System.out.println("添加数据");
                        int item = scanner.nextInt();
                        queue.addQueue(item);
                        break;
                        case 2:
                            queue.delete();
                            break;
                        case 3:
                            System.out.println("数据为:");
                            queue.showAll();
                            break;
                        case 4:
                            item = queue.peek();
                            System.out.println("第一个数据是"+ item);
                            break;
                        default:
                            return;
                    }
                }
                    catch (Exception e ){
                        System.out.println(e);
                    }
        }
    }

    /**
     * 循环队列和普通队列的区别
     *
     * 循环队列 头是指向第一个队头的           尾指向的是最后一个元素的下一个位置  空出来了一个位置
     * 普通队列 头的下一个才指向队列的第一个元素  尾指针就是指向最后一个元素的  没有空出位置
     * 判断为满的条件是
     *      循环队列：(尾指针+1)%maxsize == 头指针
     *      普通队列： 尾 == maxsize - 1
     * 判断为空的条件是
     *     两个系统  头 == 尾
     * 判断
     *
     */
    public static class Queue{
        private int maxSize; //记录最大存储个数
        private int rear ; //头指针
        private int front;//尾指针
        private int dataItem[]; //存储数量

        //初始化
        Queue(int maxSize){
            this.maxSize = maxSize;
            this.dataItem = new int[maxSize];
            //初始设定为0
            rear = front = 0;
        }
        //插入数据
        void addQueue(int item){
            //判断队列是否满了
            if(isFull()){
                throw new RuntimeException("队列满了");
            }
            dataItem[rear] = item;
            // 0 1  4
            rear = (rear+1)%maxSize;
        }
        //出队
        int delete(){
            //判断队列是否为空
            if(isEmpty()){
                throw new RuntimeException("队列为空");
            }
            int value = dataItem[front];
            front = (front+1)%maxSize;
            return value;
        }
        //判断队列是否为空
        boolean isEmpty(){
            return rear == front ;
        }
        //判断队列是否为满
        boolean isFull(){
            return (rear+1)%maxSize == front ;
        }
        //查看队头元素
        int peek(){
            if (isEmpty()){
//                    System.out.println("空");
                throw new RuntimeException("为空");
            }
            return dataItem[front];
        }
        void  showAll(){
            if (isEmpty()){
                System.out.println("为空");
                return;
            }
            /**
             * front 1
             * rear 2
             *
             */
            for (int i = front ; i <front + (rear+maxSize-front)%maxSize; i++) {
                    System.out.println("["+i+"]" + dataItem[i]);
            }

        }
    }
}
