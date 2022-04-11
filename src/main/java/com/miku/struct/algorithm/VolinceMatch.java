package com.miku.struct.algorithm;

/**
 * 字符串匹配 暴力匹配
 */
public class VolinceMatch {
    public static void main(String[] args) {
        String str1 = "i love miku ";
        String str2 = "miku";
        int index = VoliceMatch(str1,str2);
        System.out.println(index);
    }

    /**
     * 暴力匹配字符串相等
     * @param str1
     * @param str2
     * @return
     */
    public static int VoliceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Length = s1.length;
        int s2Length = s2.length;

        int i , j ;
        i = j = 0;
        //保证不越界
        while (i<s1Length && j<s2Length){
            //字符相等
            if (s1[i] == s2[j]){
                j++;
                i++;
            }else {
                i = i - (j - 1) ;
                j = 0 ;
            }
        }
        if (j == s2Length){
            return i - j ;
        }
        return -1;

    }
}
