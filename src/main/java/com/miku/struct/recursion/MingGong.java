package com.miku.struct.recursion;

/**
 * 利用递归解决迷宫回溯问题
 */
public class MingGong {
    public static void main(String[] args) {
        //画一个迷宫地图出来
        int [][] map = new int[8][7];
        //把上下两排变成qiang
        for (int i = 0 ; i < 7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右两排
        for (int i = 0 ; i < 7 ;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设子3 1 3 2为挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出这个地图
        for (int i = 0 ; i < 8 ;i++){
            for (int j = 0 ; j < 7 ; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        //调用递归开始回溯
        goHome(map,1,1);
        System.out.println("------------------------------");
        for (int i = 0 ; i < 8 ;i++){
            for (int j = 0 ; j < 7 ; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     *  一些约定
     *  地图的设定  0为为走过的路 1 为墙 2为走过的路能走的路  3为走过但是走不通的死路标记
     *  设定在6 5 为目的地
     *  行动的策略是 下 右 上 左
     * @param map  地图
     * @param i 开始的横坐标
     * @param j 开始的纵坐标
     * @return
     */
    public static Boolean goHome(int[][] map , int i , int j){

        if (map[6][5] == 2){
            //目的地为2就直接退出不用继续走了
            return true;
        }else {
                if(map[i][j] == 0){
                //此时这个路还没有走过
                map[i][j]=2;
                //判断如果下面能走就往下走 然后继续判断
                if (goHome(map,i+1,j)){
                    return true;
                    //判断右边能走就继续往下走 然后继续走
                }else if ( goHome(map,i,j+1)){
                    return true;
                    //判断上边能走就继续往下走 然后继续走
                }else if (goHome(map,i-1,j)){
                    return true;
                    //判断左边能走就继续往下走 然后继续走
                }else if (goHome(map,i,j-1)){
                    return true;
                }else {
                    //如果都不能走就判断为死路 跳出这次递归
                    map[i][j]=3;
                    return false;
                }
            }
        }
        return false;
    }
}

