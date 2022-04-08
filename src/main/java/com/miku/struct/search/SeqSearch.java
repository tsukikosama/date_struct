package com.miku.struct.search;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,5,8,45,2,6,1,51};
        int flag = seqSearch(arr, 5);
        if (flag!=-1){
            System.out.println(flag);
        }else {
            System.out.println("没有找到");
        }
    }
    public static int seqSearch(int[] arr,int target){
        for (int i = 0 ; i < arr.length ; i++){
            if (arr[i] == target){
                return i;
            }
        }
        return -1;
    }
}
