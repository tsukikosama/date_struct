package com.miku.struct.algorithm;

import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //
        char[] date = {'A','B','C','D','E','F','G'};
       myGroup group =  new myGroup(date.length);
       int[][] weight = {
               {1000,5,7,1000,1000,1000,2},
               {5,1000,1000,9,1000,1000,3},
               {7,1000,1000,1000,8,1000,4},
               {1000,9,1000,1000,1000,4,1000},
               {1000,1000,8,1000,1000,5,4},
               {1000,1000,1000,4,5,1000,6},
               {2,3,1000,1000,4,6,1000},
       };
       minTree tree = new minTree();
       tree.createTree(group,date, date.length, weight);
       tree.showTree(group);
       tree.prim(group,1);
    }
    public static class minTree{
        public void createTree(myGroup group,char[] date, int vertx,int[][] weight){
            for (int i = 0 ;i<vertx;i++){
                group.date[i] = date[i];
                for (int j = 0 ; j<vertx;j++){
                    group.weight[i][j] = weight[i][j];
                }
            }
        }
        public void showTree(myGroup group){
            for (int[] k : group.weight){
                System.out.println(Arrays.toString(k));
            }
        }

        /**
         * p普利姆算法
         * @param group
         * @param start
         */
        public void prim(myGroup group , int start){
            showTree(group);
            //记录已经访问过的节点
            int[] visit = new int[group.vertx];

            //第一个设定为已经访问过
            visit[start] = 1;
            // 用来记录访问的节点
            int h1 = -1;
            int h2 = -1;
            //用来记录最小的节点
            int minHeight = 1000;
            //遍历边数 一共有vertx -1条
            for (int i = 0 ; i < group.vertx-1 ; i++){
                //遍历节点 假定 j是已经访问过的  k是未访问过的
                for (int j = 0 ; j<group.vertx;j++){
                    for (int k = 0 ; k < group.vertx ;k++){
                        //这三个条件满足就代表这个点是没有访问过的
                        //假定 j是访问过的  k是没反问过的  然后 并且 权值比记录的最小的权值还要小  就把当前节点的 坐标记录下来 然后继续判断
                        if (visit[j] == 1 && visit[k] == 0 && group.weight[j][k] < minHeight){
                            minHeight = group.weight[j][k];
                            h1 = j;
                            h2 = k;
                        }
                    }
                }
                //走出来就找到了一个边 输出
                System.out.println("节点<"+group.date[h1]+"节点"+group.date[h2]+">"+minHeight);
                //然后把k标记为 访问过的
                visit[h2] = 1;
                //重置最小节点
                minHeight = 1000;
            }
        }
    }


    /**
     * 图
     */
    public static class myGroup{

         //节点个数
        int vertx;
        //存放数据
        char[] date;
        //存放权重
        int[][] weight;
        myGroup(int vertx){
            this.vertx = vertx;
            date = new char[vertx];
            weight = new int[vertx][vertx];
        }
    }
}
