package com.lc.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 * Input: nums = []
 * Output: []
 *
 * Example 3:
 * Input: nums = [0]
 * Output: []
 */

public class LC15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // sort the array
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0; i<n-2; i++) {
            // if nums[i] > 0, impossible to find the solution
            // firstly, find the valid nums[i]
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int target = 0 - nums[i];

            int low = i+1;
            int high = n-1;
            while(low < high) {
                int tmpSum = nums[low] + nums[high];
                if(tmpSum == target) {
                    res.add(Arrays.asList(new Integer[] {nums[i], nums[low], nums[high]}));
                    low++;
                    while(low < high && nums[low] == nums[low-1]) {
                        low++;
                    }
                    high--;
                    while(low < high && nums[high] == nums[low+1]) {
                        high--;
                    }
                } else if (tmpSum < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }
}
