package com.miku.struct.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找  通过递归实现
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {14,52,85,62,410,410,410};
        List<Integer> index = binarySearch2(arr, 0, arr.length - 1, 410);
        System.out.println(index);
    }

    /**
     * 通过递归实现二分查找
     * @param arr 需要查找的数组
     * @param left 左边开始的位置
     * @param right 右边结束的位置
     * @param findValue 需要找的值
     * @return
     */
    public static int binarySearch(int[] arr , int left, int right,int findValue){
        //这个是已经找不到的情况
        if (left > right){
            return -1;
        }
        //找到中间的位置
        int mid = (left + right)/2;
        //中间值比找的值大 要往左边递归
        if (arr[mid]>findValue){
            return binarySearch(arr,left,mid-1,findValue);
        }else if (arr[mid] < findValue){
            return binarySearch(arr,mid+1,right,findValue);
        }else {
            //找到的情况
            return mid;
        }
    }

    /**
     * 通过递归实现二分查找
     * @param arr 需要查找的数组
     * @param left 左边开始的位置
     * @param right 右边结束的位置
     * @param findValue 需要找的值
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr , int left, int right, int findValue){
        //这个是已经找不到的情况
        if (left > right){
            return new ArrayList<>();
        }
        //找到中间的位置
        int mid = (left + right)/2;
        //中间值比找的值大 要往左边递归
        if (arr[mid]>findValue){
            return binarySearch2(arr,left,mid-1,findValue);
        }else if (arr[mid] < findValue){
            return binarySearch2(arr,mid+1,right,findValue);
        }else {
            //找到的情况
            int temp = mid-1;
            List<Integer> list =  new ArrayList<>();
            //往左边找看看 是不是有相同的
            while (true){
                //左边没有数了 或者 左边的不是
                if (temp<0 || arr[temp] != findValue){
                    break;
                }
                list.add(temp);
                temp-=1;
            }
            temp = mid+1;
            //往右边边找看看 是不是有相同的
            while (true){
                //左边没有数了 或者 左边的不是
                if (temp > arr.length-1 || arr[temp] != findValue){
                    break;
                }
                list.add(temp);
                temp+=1;
            }
            //把中间的那个加入
            list.add(mid);
            return list;
        }
    }
}
