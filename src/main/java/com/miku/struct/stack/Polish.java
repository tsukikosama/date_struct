package com.miku.struct.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算逆波兰表达式
 */
public class Polish {
    public static void main(String[] args) {

        //中缀表达式转后缀表达式
        String midStr ="1+((2+3)*4)-5";
        //先把 这个中缀表达式的字符串变成一个list集合
        List<String> list1 = stringtoList(midStr);
        System.out.println(list1);
        //获取到了List集合该把他变成后续表达式了
        List<String> suffix = getSuffix(list1);
        System.out.println(suffix);
        int result = getResult(suffix);
        System.out.println(result);
//        //原式 （3+40）*5--6
//        String suffixStr = "3 40 + 5 * 6 -";
//        List<String> list = getString(suffixStr);
//        int num = getResult(list);
//        System.out.println("逆波兰表达式3 40 + 5 * 6 -的结果为:"+num);
    }

    /**
     * 变成逆波兰表达式
     * @param list
     * @return
     */
    public static List<String> getSuffix(List<String> list){
        //定义一个字符栈
        Stack<String> s1 = new Stack<>();
        //定义一个反数字的   因为 数字栈到时候还得倒  数字栈没有弹出操作就用一个list集合代替
        List<String> s2 = new ArrayList<>();
        //循环遍历判断没个字符
        for (String item : list){
            //判断是否是数字
            if (item.matches("\\d+")){
                //是数字 直接入栈
                s2.add(item);
                //判断是否是(括号 或者符号栈为空
            }else if (s1.size()==0||item.equals("(")){
                s1.add(item);
            }else if (item.equals(")")){
                //如果不是(就一直弹出 添加倒s1中
                while (!s1.peek().equals("(") && s1.size() > 0){
                    s2.add(s1.pop());
                }
                //把右括号弹出去
                s1.pop();
            }else {
                //判断栈顶的元素 优先级是不是高于当前的这个符号
                while (s1.size() != 0 && operWeight.getWeight(s1.peek()) >= operWeight.getWeight(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }

            }


        //把s1中全部的东西加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;
    }

    public static List<String> stringtoList(String str){
        //定位的索引
        int index = 0;
        //一个用于拼接的的变量
        String strTemp  ;
        //用于存贮遍历到的字符
        char c;
        //用于存储字符
        List<String> list = new ArrayList<>();
       do{

             //判断是数字
            if ((c=str.charAt(index)) < 48 || (c=str.charAt(index))>57){
                //不是数字 直接添加
                list.add("" + c);
                index++;
            }else{
                //是数字
                //判断后面是否还有数字
                strTemp = "";
                while (index<str.length()&&(c=str.charAt(index))>=48 && (c=str.charAt(index))<=57){
                    strTemp +=c;
                    index++;
                }
                list.add(strTemp);
            }

        }while (index < str.length());
        return list;
    }


    /**
     * 传入一个逆波兰表达式的集合 计算出表达式结果
     * @param list
     * @return
     */
    public static int getResult(List<String> list){
        //创建一个栈来存数据
        Stack<String> numberStack = new Stack<>();
        //定义两个变量和一个结果来存数据
        int num1 , num2 ,result;
        //遍历这个字符数组
        for (String k : list){
            //如果是数字就入栈
            if (k.matches("\\d+")){
                //入栈
                numberStack.add(k);
            }else {
                //不是数字进行计算 然后继续入栈
                //获取栈顶元素
                num1 = Integer.parseInt(numberStack.pop());
                num2 = Integer.parseInt(numberStack.pop());
                //判断符号的类型
                if (k.equals("+")){
                    result = num1 + num2;
                }else if (k.equals("-")){
                    result = num2 - num1 ;
                }else if (k.equals("*")){
                    result = num1 * num2;
                }else if (k.equals("/")){
                    result = num2 / num1 ;
                }else {
                    throw new RuntimeException("符号错误");
                }
                //计算完了结果把result 入栈
                numberStack.add(result+"");
            }
        }
        return Integer.parseInt(numberStack.pop());
    }
    /**
     * 把逆波兰表达式变成一个字符串集合
     * @param str
     * @return
     */
    public static List<String> getString(String str){
        //创建一个存储数据的集合
        List<String> list = new ArrayList<>();
        //获取每一个符号
        String[] s = str.split(" ");
        //遍历存到集合中
        for (String k : s){
            list.add(k);
        }
        return list;
    }
}
class operWeight{
    static int ADD = 1;
    static int SUB = 1;
    static int MUL = 2;
    static int DIV = 2;
    public static int getWeight(String str){
        int result = 0;
        switch (str){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("符号异常");
                break;
        }
        return result;
    }
}
