package com.miku.struct;

import java.util.Scanner;

/**
 * 为处理异常 用
 */
public class QueueDemo {
    public static void main(String[] args) {
        //队列测试
        boolean flag = true ;
        Scanner scanner = new Scanner(System.in);
         Queue queue = new Queue(3);
        while (flag){

            System.out.println("1 添加数据");
            System.out.println("2 删除数据");
            System.out.println("3 显示数据");
            System.out.println("4 显示对头");
            int k = scanner.nextInt();
            switch (k){
                case 1:
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
    }
        public static class Queue{
            private int maxSize; //记录最大存储个数
            private int rear ; //头指针
            private int front;//尾指针
            private int dataItem[]; //存储数量

            //初始化
            Queue(int maxSize){
                this.maxSize = maxSize;
                this.dataItem = new int[maxSize];
                rear = front = -1;
            }
            //插入数据
            void addQueue(int item){
                //判断队列是否满了
                if(isFull()){
                    throw new RuntimeException("队列满了");
                }
                front++;
                dataItem[front] = item;
            }
            //出队
            int delete(){
                //判断队列是否为空
                if(isEmpty()){
                    throw new RuntimeException("队列为空");
                }
                rear++;
                return dataItem[rear];
            }
            //判断队列是否为空
            boolean isEmpty(){
                return rear == front ;
            }
            //判断队列是否未满
            boolean isFull(){
               return front == maxSize-1 ;
            }
            //查看队头元素
            int peek(){
                if (isEmpty()){
//                    System.out.println("空");
                    throw new RuntimeException("为空");
                }
               return dataItem[rear+1];
            }
            void  showAll(){
                if (isEmpty()){
                    System.out.println("为空");
                    return;
                }else {
                    for (int i : dataItem) {
                        System.out.println(i);
                    }
                }
            }
    }
}


