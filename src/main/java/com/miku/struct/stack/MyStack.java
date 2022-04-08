package com.miku.struct.stack;

public class MyStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        System.out.println(arrayStack.isEmpty());
        arrayStack.addStack(1);
        //arrayStack.printAll();
        arrayStack.addStack(3);
        arrayStack.printAll();
        System.out.println(arrayStack.pop());
        //arrayStack.printAll();
        arrayStack.addStack(3);
        arrayStack.addStack(3);
        System.out.println(arrayStack.isFull());
        arrayStack.printAll();
        arrayStack.peek();
    }

}

class ArrayStack{
    public   int maxSize;
    public  int date[];
    public  int top ;


    ArrayStack(int num){
        this.maxSize = num ;
        this.date = new int[num];
        this.top = -1 ;
    }

    public void printAll(){
        if (isEmpty()){
            System.out.println("为空");
            return;
        }
        for (int i = top ; 0 <= i ; i--){
            System.out.println(date[i]);
        }
    }

    /**
     * 出栈
     */
    public int pop(){
        //判断是否为空
        if (isEmpty()){
            throw new RuntimeException("为空");
        }
        int value = date[top];
        top--;
        return value;
    }
    /**
     * 入栈
     * @param item
     */
    public void addStack(int item){
        //判断是否满了
        if (isFull()){
            System.out.println("满了 不能添加");
            return;
        }
        top++;
        date[top] = item;
        System.out.println("添加完成");
    }

    /**
     * 判断是否满
     * @return
     */
    public boolean isFull(){
        return  top == maxSize-1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 查看栈顶元素
     */
    public void peek(){
        System.out.println(date[top]);
    }

}
