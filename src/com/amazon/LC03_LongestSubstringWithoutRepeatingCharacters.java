package com.amazon;

import java.util.HashMap;

public class LC03_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0, right = 0;
        int res = 0; // 记录结果
        char[] cArray = s.toCharArray();
        while (right < cArray.length) {
            char c = cArray[right];
            right++;
            // 进行窗口内数据的一系列更新
            if(windowMap.containsKey(c)) {
                windowMap.put(c, windowMap.get(c)+1);
            } else {
                windowMap.put(c,1);
            }
            // 判断左侧窗口是否要收缩
            while (windowMap.get(c) > 1) {
                char d = cArray[left];
                left++;
                // 进行窗口内数据的一系列更新
                windowMap.put(d, windowMap.get(d)-1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "abcc";
        int res = lengthOfLongestSubstring(test);
    }
}
