package com.miku.struct.algorithm;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMP {
    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String str1 = "ABCDABD~";
        int[] next = getNext(str1);
        System.out.println(Arrays.toString(next));
        int index = KPM(str, str1, next);
        System.out.println("index:"+index);

    }

    /**
     * kmp算法  判断两个字符串是否相等 相等返回第一个索引 否则返回-1
     * @param str1
     * @param str2
     * @param next  匹配表
     * @return
     */
    public static int KPM(String str1,String str2,int[] next){
        /**
         * 遍历判断
         */
        for (int i=0,j=0;i<str1.length();i++){
            //如果不相等 就退回前一个
            if (j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            //相等就人j++比较下一个
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            //如果j和比较的字符串长度一样就比较完了
            if (str2.length() == j){
                return i - j + 1;
            }
        }
        return -1;
    }
    /**
     * 获取部分匹配表
     * @param str
     * @return
     */
    public static int[] getNext(String str){
        int[] next = new int[str.length()];
        //如果只有一个周目肯定为0
        next[0] = 0 ;
        //i从第二个数开始  j从第一个数开始 遍历到最后一个
        for (int i = 1 ,j = 0 ; i < str.length() ; i++ ){
            // 如果i 和j不相等 就让j后退一个 判断是否相等 判断到相等为止
            if (j>0 && str.charAt(i)!= str.charAt(j)){
                j = next[j-1];
            }
            //如果相等 就 让j前进一个 然后把的值记入进去
            if (str.charAt(i) == str.charAt(j)){
                j++;
                next[i] = j;
            }
        }
        return next;
    }
}
