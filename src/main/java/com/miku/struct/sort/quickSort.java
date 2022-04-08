package com.miku.struct.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = {-9,0,78,0,23,-576,70};
        quickSort(arr,0,arr.length-1);

    }

    /**
     *
     * @param arr  需要排序的数组
     * @param left 左边的起始位置
     * @param right 右边的起始位置
     */
    public static void quickSort(int[] arr,int left ,int right){
        //定义一个开始位置的左右指针
        int l = left;
        int r = right ;
        //确定中间的数的值
        int pivot = arr[(l+r)/2];
        //定义一个中间变量 去交换用
        int temp;
        while (l<r) {
            //判断左边的值是否符合
            while (arr[l]<pivot){
                l+=1;
            }
            //判断左边的是否符合
            while (arr[r]>pivot){
                r-=1;
            }
            //用于判断是否交换完成
            if (l >= r){
                break;
            }
            System.out.println(l+":"+r+"前"+Arrays.toString(arr));
            //当他们都退出循环了  这个时候 left 和 right 就是需要交换是数
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp ;

            ////如果遇到了和中间的数相等的数 就移动一下
            if(arr[l] == pivot){
                r-=1;
            }
            //如果遇到了和中间的数相等的数 就移动一下
            if (arr[r] == pivot){
                l+=1;
            }
            System.out.println(l+":"+r+"后"+Arrays.toString(arr));
        }

        //就到了中间的位置 不位移一下会照成栈溢出问题 移动一下 就可以当作下次递归的左右界限
        if (l == r){
            l+=1;
            r-=1;
        }

        //如果左边数组中只有一个数就会停止了
        if (left < r){
            quickSort(arr,left,r);
        }
        //右边数组中只有一个数就会停止了
        if (right > l){
            quickSort(arr,l,right);
        }

    }
}
