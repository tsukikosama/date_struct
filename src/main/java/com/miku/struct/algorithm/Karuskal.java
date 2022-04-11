package com.miku.struct.algorithm;

import java.util.Arrays;

public class Karuskal {
    //边的数量
    private  int edgeNum ;
    //记录顶点个数
    private  char[] vertex;
    //邻接矩阵
    private  int[][] graph ;
    //表示不连通
    public static final int INF = 1000;
    public static void main(String[] args) {
        Karuskal karuskal = new Karuskal();
        //传教节点个数
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] graph = {
                {0,12,1000,1000,1000,16,14},
                {12,0,10,1000,1000,7,1000},
                {1000,10,0,3,5,6,1000},
                {1000,1000,3,0,4,1000,1000},
                {1000,1000,5,4,0,2,8},
                {16,7,6,1000,2,0,9},
                {14,1000,1000,1000,1000,9,0}
        };

        karuskal.Karuskal(graph,vertex);

        karuskal.print();
        Edata[] edges = karuskal.getEdges();

        karuskal.sort(edges);

        karuskal.karuskal();
    }
    

         public  void Karuskal(int[][] graph,char[] vertex){
            //获取顶点个数
            int len = vertex.length;
            this.vertex = new char[len];
            this.graph = new int[len][len];
            //给顶点赋值
            for (int i = 0 ; i<len ; i++){
                this.vertex[i] = vertex[i];
            }
            for (int i = 0 ; i<len;i++){
                for (int j = 0 ;j < len ;j++){
                    this.graph[i][j] = graph[i][j];
                }
            }
             for (int i = 0 ; i < len ;i++){
                 for (int j = i+1 ; j < len ; j++ ){
                     if (graph[i][j] != INF ){
                         edgeNum++;
                     }
                 }

             }
        }

    /**
     * 输出
     */
    public void print(){
            int len = vertex.length;
            for (int i = 0 ; i < len ;i++){
                for (int j = 0 ; j < len ; j++ ){
                    System.out.printf("%5d",graph[i][j]);

                }
                System.out.println();
            }
        }

    /**
     * 冒泡排序
     * @param edata
     */
    public void sort(Edata[] edata){
        for (int i = 0 ;i< edata.length-1;i++){
            for (int j = 0 ; j < edata.length-i-1;j++){
                if (edata[j].weight>edata[j+1].weight){
                    Edata temp = edata[j+1];
                    edata[j+1] = edata[j];
                    edata[j] = temp;
                }
            }
        }
     }

    /**
     * 获取顶点下标
     * @param ch
     * @return
     */
     public int getPosition(char ch){
        for (int i = 0 ; i < vertex.length;i++){
            if (vertex[i] == ch){
                return i;
            }
        }
        return -1;
     }

    /**
     * 获取所有联通的变
     *
     * @return
     */
     public Edata[] getEdges(){
         int index = 0 ;
        Edata[] edata = new Edata[edgeNum];
        for (int i =0 ; i < vertex.length;i++){
            for (int j = i+1;j< vertex.length;j++){
                if (graph[i][j] != INF){
                    edata[index] = new Edata(vertex[i],vertex[j],graph[i][j]);
                    index++;
                }
            }
        }
        return edata;
     }

    /**
     * 卡鲁斯卡尔算法
     */
    public void karuskal(){
        //用来记录加入的边
         int index = 0 ;
         //用来保存最小生成树中的中点
        int[] ends = new int[edgeNum];
         //用来保持生成的最小生成树
        Edata[] rest = new Edata[edgeNum];

        //获取所有保存的边
        Edata[] edges = getEdges();

        //对边进行排序
        sort(edges);

        //开始获取最小生成树
        for (int i = 0; i <edgeNum;i++){
            //获取当前节点的开始节点
         int p1 = getPosition(edges[i].start);
            //获取当前节点的结束节点
            int p2 = getPosition(edges[i].end);

            //获取他们在已有生成树中的节点
            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);
            //判断他们的终点是否形成回路
            if (m != n){
                ends[m] = n;
                rest[index] = edges[i];
                index++;
            }
        }
        //输出最小生成树
        for (int i = 0 ; i < rest.length;i++){
            if (rest[i] != null) {
                System.out.println(rest[i]);
            }
        }
    }

    /**
     * 获取下标为i的顶点的终点 用于后面判断两个点的终点是否相同
     * @param ends
     * @param i
     * @return
     */
     public int getEnd(int[] ends ,int i){
         while (ends[i] != 0){
             i = ends[i];
         }
         return i;
     }
}
    class Edata{
        char start;
        char end;
        int weight;

        public Edata(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edata{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }
