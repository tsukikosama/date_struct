package com.miku.struct.algorithm;

import java.util.Arrays;

/**
 * 背包问题
 */
public class BagSpace {
    public static void main(String[] args) {
        //定义物品的重量
        int[] height = {1,4,3};
        //定义物品的价值
        int[] value = {1500,3000,2000};
        //定义背包的容量
        int m = 4 ;
        //定义物品的重量
        int n = value.length;


        //定义背包的大小
        int[][] bag = new int[n+1][m+1];
        //用来存 放入了什么东西
        int[][] path = new int[n+1][m+1];
        //把第一行第一列都置0
        for (int i = 0 ; i < bag.length;i++){
            bag[i][0] = 0;
        }
        for (int i = 0 ; i <bag[0].length;i++){
            bag[0][i] = 0;
        }
        ///// i就是获取到 背包可以存放的重量   1开始 是因为把第一行置0了 所以直接从第一行第一列开始
        ///// j就是可以放入背包物品的种类
        for (int i = 1 ; i <bag.length;i++){
            for (int j = 1 ; j < bag[i].length;j++){
                //判断当前背包的重量是否能加入当前商品
                //不能加 就用上一次加入的物品
                if (height[i-1]>j){
                    bag[i][j] = bag[i-1][j];
                }else {
                    //能加 判断 上一次加入物品的价值 和 这一次加入物品的价值哪个大  这次大就用这次的  上一次大就用上一次的
                    if (bag[i-1][j] < value[i-1]+bag[i-1][j-height[i-1]]) {
                        //把这次物品的价格放入背包中
                        bag[i][j] = value[i-1]+bag[i-1][j-height[i-1]];
                        //记录下存放了什么东西
                        path[i][j] = 1 ;
                    }else {
                        bag[i][j] = bag[i-1][j];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(path[0]));
        System.out.println(Arrays.toString(path[1]));
        System.out.println(Arrays.toString(path[2]));
        System.out.println(Arrays.toString(path[3]));

        int i = bag.length - 1 ;
        int j = bag[0].length -1 ;
        //放着遍历的
        while (i>0 && j > 0){
            if (path[i][j] == 1 ){
                System.out.println("第"+i+"个商品被添加进去了");
                j -= height[i-1];
            }
            i--;
        }
    }
}
