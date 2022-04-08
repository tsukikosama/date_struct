package com.miku.struct.recursion;

import com.miku.struct.QueueDemo;

/**
 * 8皇后问题
 *
 */
public class Queen8 {
    //定义一个8最大的数
    int max = 8 ;
    // 一维数组 实现八皇后问题 定义一个存放皇后位置的数组   索引表示第几个皇后和第几行  值表示放在的位置
    int[] queens = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }

    /**
     * 递归放置皇后的位置
     * @param n
     */
    public void check(int n ){
        //判断摆放完成
        if (n == max){
            print();
            return;
        }
        //摆放棋子的位置
        for(int i = 0; i < max ; i++){
            //把当前的皇后放置的位置
            queens[n] = i;
            //判断是否冲突
            if (judge(n)){
                //不冲突就进入递归放置下一个皇后的位置
                check(n+1);
            }
            //如果冲突的话就会移动到下一个位置 继续 循环判断
            // 如果到了最后一个位置 都不满足 就回跳出当前的这个位置 回溯到上一个皇后的位置 改变他的位置
            //然后继续进行判断
        }

    }

    /**
     * 判断和前面摆好的皇后是不是冲突
     * @param n
     * @return
     */
    public boolean judge(int n){
        //判断皇后是否冲突;
        for (int i = 0 ; i < n ; i++){
            //如果相等就在同一列
            // 1.如果当前行数的皇后的位置和前面皇后的位置系统就冲突
            //2. 用绝对值判断斜线的冲突 两个皇后所在的行数的差距 就是 斜角 冲突的位置
            if (queens[i]==queens[n] || Math.abs(queens[n]-queens[i]) == Math.abs(n - i)){
                //判断是在同一行
                return false;
            }
        }
        //如果不冲突就返回ture
        return true;
    }

    //输出皇后的位置
    public void print(){
        count++;
        for (int i =0 ; i < queens.length ; i++){
            System.out.print(queens[i] + " ");
        }
        System.out.println();
    }
}
