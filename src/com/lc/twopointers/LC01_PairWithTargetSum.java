package com.lc.twopointers;

import java.util.HashMap;
import java.util.Map;

public class LC01_PairWithTargetSum {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for(int i=0; i<n; i++) {
            index.put(nums[i],i);
        }

        for(int i=0; i<n; i++) {
            int other = target - nums[i];
            if(index.containsKey(other) && index.get(other) != i) {
                return new int[] {i, index.get(other)};
            }
        }
        return new int[] {-1, 1};
    }
}
