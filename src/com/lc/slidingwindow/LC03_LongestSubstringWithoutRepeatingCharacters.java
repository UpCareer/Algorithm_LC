package com.lc.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LC03_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int startIndex = 0;
        int endIndex = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] cArray = s.toCharArray();
        for(endIndex = 0; endIndex < cArray.length; endIndex++) {
            map.put(cArray[endIndex],map.getOrDefault(cArray[endIndex],0) + 1);
            while(map.get(cArray[endIndex]) > 1) {
                map.put(cArray[startIndex], map.get(cArray[startIndex])-1);
                startIndex++;
            }
            max = Math.max(max, endIndex - startIndex + 1);
        }
        return max;
    }


}
