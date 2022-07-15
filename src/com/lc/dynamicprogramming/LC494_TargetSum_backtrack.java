package com.lc.dynamicprogramming;

/**
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and
 * then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the
 * expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 *  Example 2:
 *
 * Input: nums = [1], target = 1
 * Output: 1
 */

public class LC494_TargetSum_backtrack {
    int result = 0;

    /* 主函数 */
    int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        backtrack(nums, 0, target);
        return result;
    }

    /* 回溯算法模板 */
    void backtrack(int[] nums, int i, int remain) {
        // base case
        if (i == nums.length) {
            if (remain == 0) {
                // 说明恰好凑出 target
                result++;
            }
            return;
        }
        // 给 nums[i] 选择 - 号
        remain += nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, remain);
        // 撤销选择
        remain -= nums[i];

        // 给 nums[i] 选择 + 号
        remain -= nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, remain);
        // 撤销选择
        remain += nums[i];
    }
}
