package com.amazon;

import java.util.HashMap;

public class LC01_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        // speed up searching efficiency
        for(int i=0; i<nums.length; i++) {
            hashmap.put(nums[i], i);
        }

        for(int i=0; i<nums.length; i++) {
            int other = target - nums[i];
            if (hashmap.containsKey(other) && hashmap.get(other) != i) {
                return new int[] {i, hashmap.get(other)};
            }
        }
        return new int[] {-1, -1};
    }
}
