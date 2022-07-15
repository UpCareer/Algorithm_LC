/*
package com.lc.az;

import java.util.ArrayList;
import java.util.List;

public class LC763 {
    public List<Integer> partitionLabels(String s) {
        // Special Case
        if (s==null || s.length()==0) {
            return null;
        }

        // Array to record the last index in the String s
        int[] arr = new int[26];
        for (int i=0; i<s.length(); i++) {
            arr[s.charAt(i)-'a'] = i;
        }

        // List to record
        int start = 0;
        int end = 0;
        for (int i=0; i<s.length(); i++) {
            end = Math.max(end, arr[s.charAt(i)-'a']);
            if (end == i)
        }
        List<Integer> res = new ArrayList<>();

        return res;
    }
}
*/
