package com.miku.struct;

/**
 * 稀疏数组
 * @author 10833
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个二维数组
        int [][] arr = new int[11][11];
        //存入一些数据
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[5][3] = 1;
        //输出这个数组
        printfArr(arr);

        //1.获取有数的个数
        int count = count(arr);
        //转换成稀疏数组
        int [][] sparseArr = new int[count+1][3];
        //2.设置稀疏数组
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = count;
        //记入添加进去的元素个数
        int countItem = 0;
        for (int i = 0 ; i<arr.length ; i++){
            for (int j = 0 ; j<arr[i].length ; j++){
                if (arr[i][j] != 0){
                    countItem++;
                    sparseArr[countItem][0]= i ;
                    sparseArr[countItem][1]= j ;
                    sparseArr[countItem][2]= arr[i][j] ;
                }
            }

        }
        printfArr(sparseArr);
      //把稀疏数组转成普通的二维数组
      int newArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
      //开始遍历存值
        for (int i = 1 ; i < sparseArr.length ; i++ ){
            newArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

       printfArr(newArr);
    }


    /**
     * 打印arr数组
     * @param arr
     */
    public static void printfArr(int [][] arr){
        for (int[] arrs : arr){
            for (int item : arrs){
                System.out.printf(item +" ");

            }
            System.out.println();
        }
    }

    public static int count(int [][] arr){
        int sum = 0;
        for (int[] arrs : arr){
            for (int item : arrs){
                if (item != 0){
                    sum++;
                }
            }
            System.out.println();
        }
        return sum;
    }
}
