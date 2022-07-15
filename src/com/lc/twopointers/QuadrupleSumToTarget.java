package com.lc.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of unsorted numbers and a target number, find all unique quadruplets in it, whose sum is equal to
 * the target number
 *
 * Example 1:
 * Input:[4,1,2,-1,1,-3], target = 1
 * Output: [-3,-1, 1, 4], [-3,1,1,2]
 * Explanation: Both the quadruplets add up to the target
 *
 * Example 2:
 * Input:[2,0,-1,1,-2,2], target = 2
 * Output: [-2,0, 2, 2], [-1,0,1,2]
 * Explanation: Both the quadruplets add up to the target
 */
public class QuadrupleSumToTarget {
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> quadruplets = new ArrayList<>();
        for(int i=0; i<arr.length-3; i++) {
            if(i > 0 && arr[i] == arr[i-1]) { // skip same element to avoid duplicate quadruplets

            }

        }
        return null;
    }
}
