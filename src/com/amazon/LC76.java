package com.amazon;

import java.util.HashMap;

public class LC76 {
    public static String minWindow(String s, String t) {
        // Initialize needMap and windowMap
        HashMap<Character, Integer> needMap = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            if(!needMap.containsKey(c) && !windowMap.containsKey(c)) {
                needMap.put(c, 1);
                windowMap.put(c, 0);
            } else if (needMap.containsKey(c)) {
                needMap.put(c, needMap.get(c) + 1);
            }
        }

        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (needMap.containsKey(c)) {
                windowMap.put(c, windowMap.get(c)+1);
                if (windowMap.get(c).equals(needMap.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == needMap.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (needMap.containsKey(d)) {
                    if (windowMap.get(d).equals(needMap.get(d))) {
                        valid--;
                    }
                    windowMap.put(d, windowMap.get(d)-1);
                }
            }
        }
        // 返回最小覆盖子串
        return minLen == Integer.MAX_VALUE ?
                "" : s.substring(start, start+minLen);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = minWindow(s,t);
        System.out.println(res);
    }
}


