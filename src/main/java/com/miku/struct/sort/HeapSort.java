package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 队排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        headSort(arr);

    }

    public static  void headSort(int[] arr){
        //用于存储临时变量
        int temp = 0;
        //循环之后就变成了大顶堆
        for (int i = arr.length/2-1; i >=0;i--){
            Sort(arr,i,arr.length);
        }
        //
        for (int j = arr.length-1 ; j > 0 ; j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            Sort(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 大顶堆怕排序
     * @param arr  需要排序的二叉树(数组)
     * @param i    记录当前非叶子节点的索引
     * @param length   记录需要排序元素的个数
     */
    public static  void Sort(int[] arr, int i,int length){
        //定义一个临时变量记录需要变化的数值
        int temp = arr[i];
        for (int j = 2*i+1;j < length ; j=j*2+1){
            //判断是否左右子树哪个大  如果右子树大就移动到左子树 否则不变
            if (j+1<length &&arr[j] < arr[j+1]){
                j++;
            }
            if (arr[j] > temp){
                //把最大值赋值给堆顶
                arr[i] = arr[j];
                //移动到改变位置的子树 循环比较
                i = j;
            }else {
                break;
            }
        }
        //把temp值反倒调整之后的位置
        arr[i] = temp;

    }
}
