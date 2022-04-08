package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 选择排序
 *     就是假定当前的位置为最小值 然后记录下来
 *     再去与后面的数 一一比较 最后将获得的震最小值和索引 赋值过去
 *
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1,-5,3,-4,7,5,9} ;
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        System.out.println("********************");
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //选择排序 从小到大
    public static void selectSort(int[] arr){
        //存最小值
        int min = 0 ;
        //存最小值的索引
        int index = 0 ;
        for (int i = 0 ; i < arr.length - 1 ; i++){
            //假定当前位置是最小的  然后去循环判断
            min = arr[i];
            index = i ;
            for (int j = i +1 ; j < arr.length ;j++){
                if (arr[index] > arr[j]){
                    //如果后面的数比需要判断的位置的数要小 就把最小值和最小值的索引给替换掉
                    min = arr[j];
                    index = j ;
                }
            }
            if (index != i) {
                //已经确定了当前位置的最小值 开始赋值
                //先把当前位置的值 赋值到 找到的最小值位置  然后把最小值 赋值到当前位置
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
