package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 基数排序
 */

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }
    public static void radixSort(int[] arr){
        //定义一个二维数组 当做桶
        int[][] buckets = new int[10][arr.length];
        //定义一个一维数组来记录桶里面的数
        int[] bucketCount = new int[10];

        //遍历获取最大值
        int max = arr[0];
        for (int m = 1 ; m<arr.length;m++){
            if (arr[m]>max){
                max =  arr[m];
            }
        }
        //获取最大的位数
        int maxLength = (max+"").length();
        System.out.println(maxLength);
        //开始基数排序
        for (int x = 0,n = 1 ; x < maxLength;x++,n*=10){
            //遍历获取每个数 获取个位
            for (int i = 0 ; i < arr.length;i++){
                int c = arr[i] /n %10;
                //存进去之后然后 计数
                buckets[c][bucketCount[c]] = arr[i] ;
                bucketCount[c] +=1;
            }
            //把值放回数组中
            int index = 0 ;
            for (int j = 0 ; j < bucketCount.length;j++){
                if (bucketCount[j] != 0){
                    //判断桶中是否有存数据 有就放
                    for (int k = 0;k<bucketCount[j];k++){
                        arr[index] = buckets[j][k];
                        index++;
                    }
                }
                //把桶中记录的东西 清空 否则影响下一次 放入
                bucketCount[j] = 0;
            }
            System.out.println("第"+(x+1)+"次"+Arrays.toString(arr));
        }
        }
        /*
        //遍历获取每个数 获取个位
        for (int i = 0 ; i < arr.length;i++){
            int c = arr[i]%10;
            //存进去之后然后 计数
            buckets[c][bucketCount[c]] = arr[i] ;
            bucketCount[c] +=1;
        }
        //把值放回数组中
        int index = 0 ;
        for (int j = 0 ; j < bucketCount.length;j++){
            if (bucketCount[j] != 0){
                //判断桶中是否有存数据 有就放
                for (int k = 0;k<bucketCount[j];k++){
                    arr[index] = buckets[j][k];
                    index++;
                }
            }
            //把桶中记录的东西 清空 否则影响下一次 放入
            bucketCount[j] = 0;
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 0 ; i < arr.length;i++){
            int c = arr[i]/10%10;
            //存进去之后然后 计数
            buckets[c][bucketCount[c]] = arr[i] ;
            bucketCount[c] +=1;
        }
        //把值放回数组中
         index = 0 ;
        for (int j = 0 ; j < bucketCount.length;j++){
            if (bucketCount[j] != 0){
                for (int k = 0;k<bucketCount[j];k++){
                    arr[index] = buckets[j][k];
                    index++;
                }
            }
            bucketCount[j] = 0;
        }
        System.out.println(Arrays.toString(arr));


        for (int i = 0 ; i < arr.length;i++){
            int c = arr[i]/100%10;
            //存进去之后然后 计数
            buckets[c][bucketCount[c]] = arr[i] ;
            bucketCount[c] +=1;
        }
        //把值放回数组中
         index = 0 ;
        for (int j = 0 ; j < bucketCount.length;j++){
            if (bucketCount[j] != 0){
                for (int k = 0;k<bucketCount[j];k++){
                    arr[index] = buckets[j][k];
                    index++;
                }
            }
            bucketCount[j] = 0;
        }
        System.out.println(Arrays.toString(arr));
        */


}
