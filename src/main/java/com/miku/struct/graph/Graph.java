package com.miku.struct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 */
public class Graph {
    /**
     *
     * 用来存权zhogn
     * 记录值
     * 记录边长
     */
    public static int[][]  edges;
    public static List<String> graphValue;
    public static int vertex ;
    public static boolean[] isVisited;
    public static void main(String[] args) {
        int n = 5;
        Graph graph = new Graph(n);
        String[] values= {"A","B","C","D","E"};
        //添加数据
        for (String s : values){
            graph.insertValue(s);
        }
        //添加边
        graph.addVertex(0,1,1);
        graph.addVertex(0,2,1);
        graph.addVertex(1,2,1);
        graph.addVertex(1,3,1);
        graph.addVertex(1,4,1);

        graph.printGraph();

//        graph.dfs();
        graph.bfs();
    }

    /**
     * 遍历二维数组
     */
    public void printGraph(){
        for (int[] k : edges){
            System.out.println(Arrays.toString(k));
        }
    }

    /**
     * 获取边的数目
     * @return
     */
    public int getVertexNums(){
        return vertex;
    }
    public int getHeight(int v1 , int v2){
        return edges[v1][v2];
    }

    /**
     * 获取节点个数
     * @return
     */
    public int getValueNums(){
        return graphValue.size();
    }

    /**
     * 通过节点获取对应的值
     * @param index
     * @return
     */
    public String getValueById(int index){
        return graphValue.get(index);
    }

    /**
     * bfs 广度优先
     * @param i
     * @param isVisited
     */
    public void bfs(int i , boolean[] isVisited){
        //获取对应节点的u
        int u ;
        //下一个节点
        int w;
        //存储的队列
        LinkedList list = new LinkedList<>();
        //输出当前节点
        System.out.println(getValueById(i));
        //标记为true
        isVisited[i] = true;
        //添加进队列
        list.addLast(i);
        //判断队列是否为空
        while (!list.isEmpty()){
            //获取队列的头节点下表
            u =(Integer)list.removeFirst();
            //获取第一个临接点
            w = getNeighbor(u);
            //判断是否走过了
            while (w != -1){
                if (!isVisited[w]){
                    System.out.println(getValueById(w));
                    isVisited[w] = true;
                    list.addLast(w);
                }
                //以u为前驱 找到w后面的下一个
                w = getNextNeighbor(u,w);
            }

        }
    }
    public void bfs(){
        for (int i = 0 ; i< getValueNums();i++){
            if (!isVisited[i]){
                bfs(i,isVisited);
            }
        }
    }
    /**
     * dfs
     * @param i
     * @param isVisited
     */
    public void dfs(int i,boolean[] isVisited){
        //输出当前节点
        System.out.println(getValueById(i));
        //然后把当前节点标记为输出过了
        isVisited[i] = true;
        //获取下一个节点
        int w = getNeighbor(i);
        //循环判断下一个节点是否连通
        while (w != -1){
            //判断是否输出过了
            if (!isVisited[w]){
                //如果没有输出过就递归调用
                dfs(w,isVisited);
            }
            //如果节点已经被访问过了 就去获取他的下一个节点
            w = getNextNeighbor(i,w);
        }

    }

    /**
     * 一次dfs可能走不完 所以全部的节点都要进行一次深度优先搜索保证 每个节点都被搜索到
     */
    public void dfs(){
        for (int i = 0 ; i < getValueNums();i++){
            if (!isVisited[i]){
                dfs(i,isVisited);
            }
        }
    }

    /**
     * 获取当前节点的第一个临接节点  并且返回当前节点的下标
     *  i
     */
    public int getNeighbor(int i){
        //循环判断是否有邻接节点
        for (int j = 0 ; j < getValueNums() ; j++){
            //判断是否临界的条件就是临界表中是否大于0 大于0返回下表
            if (edges[i][j]>0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取第一个临界节点的下一个临接节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        //如果找到了 下一个i就返回当前节点的下表
        for (int j = v2+1;j < getValueNums();j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 添加边
     * @param v1
     * @param v2
     * @param height  权重
     */
    public void addVertex(int v1 ,int v2 , int height){
        edges[v1][v2] = height;
        edges[v2][v1] = height;
        vertex++;

    }
    /**
     * 添加数据进图
     * @param value
     */
    public void insertValue(String value){
        graphValue.add(value);

    }

    /**
     * 图的初始化方法
     * @param n 一共有多少个节点
     */
    public Graph(int n){
         //初始化数组 大小
        edges = new int[n][n];
        graphValue = new ArrayList<>();
        vertex = 0;
        isVisited = new boolean[n];
    }
}
