package com.miku.struct.stack;

public class Calculator {
    public static void main(String[] args) {
        String str = "7+200*6-4";
        //定义一个辅助指针 获取当前的字符位置
        int index = 0;
        //定义两个栈 一个是数字栈 用来存数字  一个是字符栈 用来存字符
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operStack =new ArrayStack2(10);
        //用于接受字符栈取出来的运算符
        int tempCh ;
        //用于接收数字栈传出来的数字
        int num1,num2;
        //用于接受运算完的结果
        int result ;
        //用于连接字符数字
        String connect = "";
        while (true) {
            //循环获取一个字符
            int ch = str.substring(index, index + 1).charAt(0);
            //判断这个字符是数字还是字符
            if (operStack.isOper(ch)){
                //是字符  然后去判断字符栈是不是为空  如果为空直接入栈  否则去比较符号的优先级
                if (operStack.isEmpty()){
                    //字符栈是空的 直接添加近栈
                    operStack.addStack(ch);
                }else{
                    //字符栈不为空  判断字符的优先级  如果比栈顶的优先级高 就近栈 否则运算掉
                    if (operStack.top < operStack.prio(ch)){
                        //比栈顶的优先级高 直接入栈
                        operStack.addStack(ch);
                    }else {
                        //比栈顶的优先级低 机算掉 然后入栈
                        tempCh = operStack.pop();
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        //把他们的结果计算出来
                        result = operStack.getResult(num1,num2,tempCh);
                        //把计算完的结果入数字栈
                        numberStack.addStack(result);
                        //字符也添加入字符栈
                        operStack.addStack(ch);
                    }

                }
            }else{
                //处理多位数 字符减去48否则会是字符的数字
                connect +=ch-48 ;
                //判断数字后面一个是不是还是数字
                //判断是不是最后一个字符
                if (index == str.length()-1){
                    //是最后一个直接添加
                    numberStack.addStack(ch-48);
                }else {
                    //不是最后一个 判断数字后面一个是不是还是数字
                    if (operStack.isOper(str.substring(index+1,index+2).charAt(0))){
                        //不是字符的情况
                        //变成数字加入数字栈
                        numberStack.addStack(Integer.parseInt(connect));
                        //清空
                        connect = "";

                    }
                }



            }
            index++;
            //判断是否到了最后一个
            if (index == str.length()){
                break;
            }
        }
        System.out.println(1);
        //进行运算 从字符栈和数字栈取值机算
        while (!operStack.isEmpty()){
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            result = operStack.getResult(num1, num2, operStack.pop());
            numberStack.addStack(result);
            if (operStack.isEmpty()){
                break;
            }
        }

        //数组栈顶元素 获取结构
        System.out.println(numberStack.peek());
    }
}

class ArrayStack2{
    public   int maxSize;
    public  int date[];
    public  int top ;


    ArrayStack2(int num){
        this.maxSize = num ;
        this.date = new int[num];
        this.top = -1 ;
    }

    /**
     * 判断是否是数字
     */
    public boolean isOper(int ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * 判断优先级
     * @param ch
     * @return
     */
    public int prio(int ch){
        if(ch == '+' || ch == '-'){
            return 0;
        }else if (ch=='*' ||ch =='/'){
            return 1;
        }else {
            System.out.println("优先级错误");
            throw new RuntimeException();
        }
    }
    /**
     * 获取计算结果
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int getResult(int num1 , int num2 , int oper){
        int result = 0;
        switch (oper){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
            default:
                System.out.println("参数有误");
                break;
        }
        return result;
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
    public int peek(){
        return date[top];
    }

}
