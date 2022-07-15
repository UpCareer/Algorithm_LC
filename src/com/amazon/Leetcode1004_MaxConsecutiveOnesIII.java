package com.amazon;

public class Leetcode1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        // sliding windows
        int i=0;
        int j=0;
        int max = 0;
        while(i < nums.length) {
            if(nums[i] == 0) k--;
            while (k < 0) {
                if (nums[j] == 0) k++;
                j++;
            }
            i++;
            max = Math.max(max,i-j);
        }
        return max;
    }
}
