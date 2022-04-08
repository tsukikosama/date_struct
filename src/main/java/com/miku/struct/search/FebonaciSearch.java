package com.miku.struct.search;

import java.util.Arrays;

public class FebonaciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("所要查找的数据的下标值index=" + feiSearch(arr, 1000));
    }

    /**
     * 得到一个斐波那契数列
     * 使用非递归方式
     *
     * @return
     */
    public static int[] fei() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法
     *
     * @param arr
     * @param key 要查找的关键字
     * @return返回对应 的下标值
     */
    public static int feiSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契数分割数的下标值
        int mid = 0;
        int[] f = fei();//调用斐波那契数列

         //获取斐波那契分割数值的下标
        while (high > (f[k] - 1)) {
            k++;
        }
    //因为f[k]值可能大于a的长度，因此需要使用Arrays工具类，构造一个新法数组，并指向temp[],不足的部分会使用0补齐
        int[] temp = Arrays.copyOf(arr, f[k]);
    //实际需要使用arr数组的最后一个数来填充不足的部分
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环处理，找到key值
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向数组的前面部分进行查找
                high = mid - 1;
                /*
                 * 对k--进行理解
                 * 1.全部元素=前面的元素+后面的元素
                 * 2.f[k]=k[k-1]+f[k-2]
                 * 因为前面有k-1个元素没所以可以继续分为f[k-1]=f[k-2]+f[k-3]
                 * 即在f[k-1]的前面继续查找k--
                 * 即下次循环,mid=f[k-1-1]-1
                 */
                k--;
            } else if (key > temp[mid]) {//向数组的后面的部分进行查找
                low = mid + 1;
    /**
     * 对k-=2理解
     * 1.全部元素=前面的元素+后面的元素
     * 2.f[k]=k[k-1]+f[k-2]
     * 3.因为后面有k-2个元素，所以可以继续拆分f[k-2]=f[k-3]+f[k-4]
     * 4.即在f[k-2]前面进行查找k-=2
     * 5.即在下次循环mid=[k-1-2]-1
     */
                k -= 2;

            } else {//找到了
        //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }


}
