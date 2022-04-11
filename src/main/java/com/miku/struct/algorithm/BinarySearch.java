package com.miku.struct.algorithm;

/**
 * 二分查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,5,42,51,76,99};
        int index = binarySearch(arr,99);
        System.out.println("index="+index);
    }
    public static  int binarySearch(int[] arr , int target){
        //获取左右指针
        int left = 0;
        int right = arr.length -1 ;
        int mid;
        while (left<=right){
            //mid每次循环都会变
             mid = (left+right)/2;
            //找到了
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1 ;
            }
        }
        return -1;

    }
}
