package com.miku.struct.sort;


import java.util.Arrays;

/**
 * 直接插入排序
 * 方法 就是把数组当成一个有序的数组和一个无序的数组
 * 默认第一个是有序的数组 然后去遍历后面的数
 * 记录需要插入的数的值
 * 去和前面的数比较
 * 到最后界替换
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = { 1,3,4,2,5};
        insertSort(arr);
    }

    //从小到大
    public static void insertSort(int[] arr){

        //记录要插入的数的值
        int insertValue ;
        //记录比较的位置
        int insertIndex ;
        //遍历插入后面的每一个数
        for (int i = 1 ; i < arr.length ; i++){
            //先记录需要交换位置的值
            insertValue = arr[i];
            //第一个要比较的位置
            insertIndex = i - 1;
            while (insertIndex >=0 && insertValue<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertValue;
            System.out.println("第"+i+"次:"+ Arrays.toString(arr));
        }

    }
}
