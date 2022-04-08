package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 实现冒泡排序
 * 遍历一个一个比较大小 一个一个确定
 */
public class BubleSort {
    public static void main(String[] args) {
        //定义一个需要排列的数组
        int [] arr = {1,5,3,9,12,4,2,20};
        //定义一个临时变量 用来存储交换的的时候的数据
        int temp ;
        boolean flag;
        //冒泡排序 排序  左小右大
        for (int i = 0 ; i< arr.length - 1 ; i++){
             flag = true;
            for (int j = 0 ; j < arr.length - 1 - i ; j++){
                //判断是否需要交换
                if (arr[j] > arr[j+1]){
                    //两个数进行交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = false ;
                }
            }
            if (flag == true){
                break;
            }
            System.out.println("交换后的结果" + Arrays.toString(arr));
        }
    }
}
