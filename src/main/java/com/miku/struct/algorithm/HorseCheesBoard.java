package com.miku.struct.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HorseCheesBoard {
    //X代表列 Y代表行
    private static  int X ;
    private static  int Y ;
    //记录位子是否走过了
    private static boolean[] isvisited;
    //如果为真就是走完了
    private static boolean finished;
    public static void main(String[] args) {
        X=8;
        Y=8;
        int row = 1;
        int col = 1;

        int[][] chessBoard = new int[X][Y];
        isvisited = new boolean[X*Y];

        Long d1 = System.currentTimeMillis();
        go(chessBoard,row-1,col-1,1);
        long d2 = System.currentTimeMillis();
        System.out.println(d2-d1);
        for (int[] r : chessBoard){
            for (int x : r){
                System.out.print(x + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 骑士周游问题的核心算法
     * @param chessBoard 棋盘
     * @param row 当前的行
     * @param col 当前的列
     * @param temp 当前是第几步
     */
     public static void go(int[][] chessBoard,int row , int col,int temp){
         chessBoard[row][col] = temp;
        //先将当前位置设定为已访问
         isvisited[row*X + col] = true;
         //获取还能走的位置的集合
         ArrayList<Point> ps= next(new Point(col,row));
         sort(ps);
         //遍历ps中所有能走的位子
         while (!ps.isEmpty()){
             //获取到当前能走的位子
             Point p= ps.remove(0);
             //判断能走的位子是否走过了
             if (!isvisited[p.y*X+p.x]) {
                 go(chessBoard, p.y, p.x, temp + 1);
             }

         }
         //判断是否走完了全部
         if (temp < X*Y && !finished){
             //把当前这个变成没走过的
             chessBoard[row][col] = 0;
             isvisited[row*X + col] = false;
         }else {
             finished = true;
         }
     }

    /**
     * 这个方法 用来获取当前位置 可以走的其他的位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        //定义一个集合去存储可以走的点
        ArrayList<Point> list = new ArrayList<>();

        //定义一个临时遍历存储数据
        Point temp = new Point();
        //判断左上位子是否可以走
        //5
        if ((temp.x = curPoint.x-2) >=0 && (temp.y = curPoint.y-1) >=0){
            list.add(new Point(temp));
        }
        //6
        if ((temp.x = curPoint.x-1)>=0 && (temp.y = curPoint.y-2) >=0){
            list.add(new Point(temp));
        }
        //右上 7
        if ((temp.x = curPoint.x+1)<X && (temp.y= curPoint.y-2) >=0){
            list.add(new Point(temp));
        }
        //0
        if ((temp.x = curPoint.x+2)<X && (temp.y = curPoint.y-1) >=0) {
            list.add(new Point(temp));
        }
        //3
        if ((temp.x =curPoint.x-1)>=0 && (temp.y = curPoint.y+2)<Y){
            list.add(new Point(temp));
        }
        //左下 4
        if ((temp.x = curPoint.x-2) >=0 &&(temp.y = curPoint.y+1)<Y){
            list.add(new Point(temp));
        }
        //1
        if ((temp.x = curPoint.x+2) <X && (temp.y = curPoint.y+1)<Y){
            list.add(new Point(temp));
        }
        //右下2
        if ((temp.x = curPoint.x+1) <X && (temp.y = curPoint.y+2) <Y){
            list.add(new Point(temp));
        }
        return list;
    }

    /**
     * 对马踏棋盘的优化
     * 当前的集合排序 规则是
     * 对下一次获取的集合能走的步数少的优先
     * 非递减的排序 (可以出现重复)
     * @param list
     */
    public static  void sort(ArrayList<Point> list){
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取o1下一次的能走的个数
                ArrayList<Point> p1 = next(o1);
                //获取o2下一次的能走的个数
                ArrayList<Point> p2 = next(o2);
                if (p1.size() < p2.size()){
                    return -1;
                }else if (p1.size() == p2.size()){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
