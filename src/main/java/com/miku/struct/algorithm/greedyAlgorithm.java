package com.miku.struct.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 */

public class greedyAlgorithm {
    public static void main(String[] args) {
        //定义一个广播集合
        HashMap<String, HashSet<String>> boardCasts = new HashMap<>();
        //添加所有的广播
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");
        //把所有广播添加进集合
        boardCasts.put("k1",set1);
        boardCasts.put("k2",set2);
        boardCasts.put("k3",set3);
        boardCasts.put("k4",set4);
        boardCasts.put("k5",set5);
        //定义一个需要服盖的地区
        HashSet<String> allArears = new HashSet<>();
        allArears.add("北京");
        allArears.add("上海");
        allArears.add("天津");
        allArears.add("广州");
        allArears.add("深圳");
        allArears.add("成都");
        allArears.add("杭州");
        allArears.add("大连");
        //存放选择的电台
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时存储的集合
        HashSet<String> temp = new HashSet<>();
        //定义一个maxkey用于存放最大的未添加的
        String maxKey =null;
        /**
         * 集合为空时候就退出
         */
        while (allArears.size() != 0){
            maxKey = null;

            for (String k : boardCasts.keySet()){
                //每次都要清楚 否则会保持上一次的数据
                temp.clear();
                //能服盖的地区
                HashSet<String> area = boardCasts.get(k);
                //然后把能服盖的地区存到temp中
                temp.addAll(area);
                //获取有几个相同的地区
                temp.retainAll(allArears);
                //然后判断是否有需要服盖的地区
                if (temp.size()>0 &&
                        //如果maxkey是第一次就是为null 判断是否有未服盖的地区 如果有就把当前广播添加进去
                        (maxKey == null || temp.size() > boardCasts.get(maxKey).size())){
                    //把当前最大为覆盖的广播记录下来
                    maxKey =  k;
                }
            }
            //判断是否需要添加进去
            if (maxKey!=null){
                //把最大的没被服盖的地区添加进去
                selects.add(maxKey);
                //把已经服盖的地区 移除 继续下一次添加
                allArears.removeAll(boardCasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
