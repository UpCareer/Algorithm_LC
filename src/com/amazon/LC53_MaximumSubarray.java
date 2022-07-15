package com.amazon;

public class LC53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // Solution 1: Brutal Force

        // Solution 2: Kadane's Algorithm == Dynamic Programming
        int max_sum = nums[0];
        int current_sum = max_sum;
        for(int i=1; i<nums.length; i++) {
            current_sum = Math.max(nums[i]+current_sum, nums[i]);
            max_sum = Math.max(max_sum, current_sum);
        }
        return max_sum;
    }
}
