package com.miku.struct.algorithm;

/**
 * 分治算法 汉诺塔问题
 */
public class HanNoiTower {
    public static void main(String[] args) {
        HanNoiTower(64,'a','b','c');
    }

    /**
     *
     * 复杂问题简单化
     * 把汉诺塔 看成最底下一个 和上面的全部为一个
     *
     * @param nums  多少个盘子
     * @param a
     * @param b
     * @param c
     */
    public static void HanNoiTower(int nums , char a, char b , char c){
        if (nums == 1){
            System.out.println("第"+nums+"个盘子从"+a +"=>"+c);
        }else {
            //把上面的盘子从a到b
            HanNoiTower(nums-1,a,c,b);
            //然后把最后一个移动到c
            System.out.println("第"+nums+"个盘子从"+ a +"=>" + c);
            //把全部从盘子从b到c
            HanNoiTower(nums-1,b,a,c);
        }
    }
}
