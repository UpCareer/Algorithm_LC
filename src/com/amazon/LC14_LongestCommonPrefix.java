package com.amazon;

public class LC14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        //strs = ["flower","flow","flight"]
        if(strs == null || strs.length == 0) {
            return "";
        }

        String pre = strs[0];
        for(int i=1; i<strs.length; i++) {
            while(!strs[i].startsWith(pre)) {
                pre = pre.substring(0, pre.length() - 1);
            }
        }
        return pre;
    }
}
