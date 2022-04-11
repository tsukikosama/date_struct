package com.miku.struct.algorithm;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        final int N = 1000;
        int[][] matrix = {
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0},
        };
        Groph groph = new Groph(vertex.length,matrix,vertex);
        groph.floyd();
        groph.show();
    }
    public static class Groph{
        char[] vertex;
        int[][] dis;
        int[][] pre;

        /**
         * 弗洛伊德算法
         */
        public void floyd(){
            //用来存储距离
            int len = 0;
            //遍历中间结点
            for(int k =0 ; k < dis.length;k++){
                //遍历开始结点
                for (int i= 0 ; i < dis.length;i++){
                    //遍历结束的结点
                    for (int j = 0 ; j < dis.length;j++){
                        //获取中转结点的距离
                        len = dis[i][k] + dis[k][j];
                        if (len < dis[i][j]){
                            //把最小的赋值给当前标
                            dis[i][j] = len;
                            //更新前驱结点
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }

        /**
         * 构造器
         * @param length
         * @param matrix
         * @param vertex
         */
        public Groph(int length,int[][] matrix,char[] vertex){
            this.vertex = vertex;
            this.dis = matrix;
            this.pre = new int[length][length];

            for (int i = 0 ; i < length ; i++){
                    Arrays.fill(pre[i],i);
            }
        }

        /**
         * 输出方法
         */
        public void show(){
            char[] chars = {'A','B','C','D','E','F','G'};
            for (int i = 0 ; i<dis.length;i++){
                for (int j = 0 ; j < dis.length;j++){
                    System.out.print( chars[pre[i][j]] +" ");
                }
                System.out.println();
                for (int k =0;k< dis.length;k++){
                    System.out.print("("+chars[i]+ "到"+ chars[k]+"最短路径"+dis[i][k] + ") ");
                }
                System.out.println();
            }
        }
    }
}
