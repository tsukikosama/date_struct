package com.miku.struct.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr);
    }

    /**
     * 希尔排序 用交换法
     *  就是通过分组来把要排序的对象 变小
     *  然后进行排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        //定义一个用于中间交换的变量
        int temp ;
        //计数器 计数分组次数
        int count = 0 ;
        /**
         * 每次的步长都在变化 如果 步长 ==0 就分完了
         */
        for (int gap = arr.length/2;gap>0;gap/=2){
            //每一次遍历都是代表一组
            for (int i = gap ; i< arr.length;i++){
                //倒着着判断
                for (int j = i - gap; j >= 0  ; j-=gap){
                    //后面的比前面的大
                    if (arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"次:"+ Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序位移法
     * @param arr
     */
    public static void shellSort2(int[] arr){
        //定义一个用于中间交换的变量
        int temp ;

        /**
         * 每次的步长都在变化 如果 步长 ==0 就分完了
         */
        for (int gap = arr.length/2;gap>0;gap/=2){
            //每一次遍历都是代表一组
            for (int i = gap ; i< arr.length;i++){
                //把需要排序的值存入这个临时变量 和记录当前需比较的索引
                int j = i;
                temp = arr[j];
                //判断 如果前面的比后面的大 就要进行交换
                if (arr[j] < arr[j-gap]){
                    //判断前面还有数  并且比后面的大
                    while (j - gap >=0 && temp < arr[j-gap]){
                        //把数往后移动
                        arr[j] =arr[j-gap];
                        //移动到前面一个数继续比较
                        j -= gap;
                    }
                }
                //遍历完了 就是需要交换倒的索引
                arr[j] = temp;

            }
            System.out.println("第"+"次:"+ Arrays.toString(arr));
        }
    }
}
