package com.miku.struct.algorithm;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        //创建图
        char[] vertex = {'A','B','C','D','E','F','G'};
        final int N = 1000;
        int[][] matrix = {
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}
        };
        Graph graph = new Graph(vertex,matrix);
        graph.print();
        graph.dijkstra(6);
        graph.show();
    }
    public static class Graph{
        private char[] vertex;
        private int[][] matrix;
        private Dis ds;

        public Graph(char[] vertex, int[][] matrix) {
            this.vertex = vertex;
            this.matrix = matrix;
        }
        public void show(){
            ds.show();
        }
        //输出方法
        public void print(){
            for (int[] k : matrix){
                System.out.println(Arrays.toString(k));
            }
        }

        /**
         * 迪杰斯特拉算法实现
         */
        public void dijkstra(int index){
            ds = new Dis(vertex.length,index);
            update(index);
            for (int i = 1 ; i< vertex.length;i++){
                int id = ds.updataArr();
                update(id);
            }
        }

        public void update(int index){
            int len = 0;
            for (int i = 0; i < matrix[index].length;i++){
                //获取当前的结点到其他的结点的距离
                len = ds.getDis(index) + matrix[index][i];
                //如果没被访问过 并且 距离小于之前记录的记录 就改变
                if (!ds.in(i) && ds.getDis(i) > len){
                    //记录最小值
                    ds.updateDis(i,len);
                    //记录前驱结点
                    ds.updatePre(index,i);
                }
            }
        }

    }
    public static class Dis{
        int[] already_arr;
        int[] pre_visited;
        int[] dis;

        /**
         * 初始化dis
         */
        public Dis(int length, int index) {
            this.already_arr = new int[length];
            this.pre_visited = new int[length];
            this.dis = new int[length];

            Arrays.fill(dis,1000);
            this.dis[index] = 0;
            this.already_arr[index] = 1;
        }

        /**
         * 判断 index顶点是否被访问过
         * @param index
         * @return  访问过返回ture  否则返回false
         */
        public boolean in(int index){
            return already_arr[index] == 1;
        }

        /**
         * 更新最小路径
         * @param length
         * @param index
         */
        public void updateDis(int index,int length){
            dis[index] = length;
        }

        /**
         * 更新前驱结点 为index
         * @param index
         * @param pre
         */
        public void updatePre(int index, int pre){
            pre_visited[pre] = index;
        }

        /**
         * 得到最小路径
         * @param index
         * @return
         */
        public int  getDis(int index){
            return dis[index];
        }

        public int updataArr(){
            int min =1000,index =0;
            for (int i = 0 ; i < already_arr.length;i++){
                //找到没访问过 并且 可以和上一个结点联通 然后 访问的值是最小的
                if (already_arr[i] != 1 && dis[i]<min){
                    min = dis[i];
                    index = i;
                }
            }
            //标记为访问过了
            already_arr[index] = 1;
            return index;
        }
        public void show(){
            for (int k : already_arr){
                System.out.print("访问过的"+k + " ");
            }
            System.out.println();
            for (int k : pre_visited){
                System.out.print("前驱结点"+k + " ");
            }
            System.out.println();
            for (int k : dis){
                System.out.print("最短路径"+k +" ");
            }
        }
    }
}
