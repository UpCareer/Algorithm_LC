package com.lc.slidingwindow;

public class LC1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;
        int start_index = 0;
        int end_index = 0;
        for(end_index=0; end_index<nums.length; end_index++) {
            if(nums[end_index] == 0) {
                k--;
            }
            while(k < 0) {
                if(nums[start_index] == 0) {
                    k++;
                }
                start_index++;
            }
            max = Math.max(max, end_index - start_index + 1);
        }
        return max;
    }

}
