package com.lc.twopointers;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted
 * in non-decreasing order.
 *
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 *
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */

public class LC977_SquaresofaSortedArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;
        int cur = n-1;
        int[] res = new int[n];
        while(l<=r) {
            if(Math.abs(nums[l]) > Math.abs(nums[r])) {
                res[cur] = nums[l] * nums[l];
                l++;
            } else {
                res[cur] = nums[r] * nums[r];
                r--;
            }
            cur--;
        }
        return res;
    }
}
