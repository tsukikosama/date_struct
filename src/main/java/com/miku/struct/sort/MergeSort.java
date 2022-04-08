package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分合治
     * @param arr
     * @param left
     * @param right
     * @param tempArr
     */
    public static void mergeSort(int[] arr , int left, int right,int[] tempArr){

        //一直分到只有一个数为止 开始递归
        if (left<right){
            int mid = (left+right)/2;
            //分
            mergeSort(arr,left,mid,tempArr);
            mergeSort(arr,mid+1,right,tempArr);

            merge(arr,left,mid,right,tempArr);
        }
    }
    /**
     * 合
     * @param arr 排序的数组
     * @param left 左边开始
     * @param mid   用于区分两边
     * @param right 右边的结束
     * @param tempArr   临时数组
     */
    public static void merge(int[] arr , int left,int mid, int right,int[] tempArr){
        //左边指针
        int i = left;
        //右边指针
        int j = mid+1;
        //临时数组的指针
        int t = 0;
        //判断两边是否到头了
        while (i<=mid && j<=right){
            //左边的比右边的小 把左边的添加进零时数组 并且移动索引
            if (arr[i]<=arr[j]){
                tempArr[t] = arr[i];
                t+=1;
                i+=1;
                //右边边的比左边的小 把左边的添加进零时数组 并且移动索
            }else {
                tempArr[t] = arr[j];
                t+=1;
                j+=1;
            }
        }
        //判断哪边空了  就把另一边剩余的数都加入进去

            while (i<=mid){
                tempArr[t] = arr[i];
                t+=1;
                i+=1;
            }


            while (j<=right){
                tempArr[t] = arr[j];
                t+=1;
                j+=1;
            }

        //将temp拷贝到arr
        //定义一个临时的左边变量 因为不一定是全部拷贝进去
        t = 0 ;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = tempArr[t];
            tempLeft+=1;
            t+=1;
        }
    }
}
