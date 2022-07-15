package com.lc.twopointers;

/**
 * Introduction:
 *
 * In problems where we deal with sorted arrays (or LinkedList) and need to find a set of elements that fulfill certain
 * constrains, the Two Pointers approach becomes quite useful. The set of elements could be a pair, a triplet or even
 * a subarray. For example, take a look at the following problem:
 *
 * Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target
 *
 *
 */

public class TwoPointers {
    public static int[] search(int[] arr, int targetSum) {
        int start = 0;
        int end = arr.length -1;

        while(start < end) {
            int sum = arr[start] + arr[end];
            if(sum > targetSum) {
                end--;
            } else if(sum < targetSum) {
                start++;
            } else {
                return new int[] {start, end};
            }
        }
        return new int[] {-1,-1};
    }
}
