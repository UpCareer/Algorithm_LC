package com.lc.dynamicprogramming;

/**
 * 子序列解题模板:最长回文子序列
 * 一. 前言
 * 一般来说，这类问题都是让你求一个最长子序列。一旦涉及到子序列和最值，考察的是动态规划技巧，时间复杂度一般都是 O(n^2)。
 * 原因很简单，你想想一个字符串，它的子序列有多少种可能？起码是指数级的吧，这种情况下，不用动态规划技巧，还想怎么着呢？
 *
 * 既然要用动态规划，那就要定义 dp 数组，找状态转移关系。我们说的两种思路模板，就是 dp 数组的定义思路。不同的问题可能需要不同的 dp 数组定义来解决。
 *
 * 二. 两种思路
 * 第一种思路模板是一个一维的dp数组：
 * int n = array.length;
 * int []dp = new int[n];
 *
 * for(int i =1;i<n;i++){
 * 	for(int j=0;j<i;j++){
 * 		dp[i] = 最值(dp[i],dp[j]+...)
 *    }
 * }
 * 举个最长递增子序列的例子，这个思路中dp数组的定义是：
 * 在子数组array[0…i]中，以array[i]结尾的目标子序列（最长递增子序列）的长度是dp[i]。
 *
 * 第二种思路模板是一个二维的dp数组：
 * int n = arr.length;
 * int[][] dp = new dp[n][n];
 *
 * for (int i = 0; i < n; i++) {
 *     for (int j = 1; j < n; j++) {
 *         if (arr[i] == arr[j])
 *             dp[i][j] = dp[i][j] + ...
 *         else
 *             dp[i][j] = 最值(...)
 *     }
 * }
 *
 * 涉及两个字符串/数组时（比如最长公共子序列），dp 数组的含义如下：
 * 在子数组arr1[0…i]和子数组arr2[0…j]中，我们要求的子序列（最长公共子序列）长度为dp[i][j]。
 * 只涉及一个字符串/数组时（比如本文要讲的最长回文子序列），dp 数组的含义如下：
 * 在子数组array[i…j]中，我们要求的子序列（最长回文子序列）的长度为dp[i][j]。
 *
 * 五.总结
 * 主要还是正确定义 dp 数组的含义，遇到子序列问题，首先想到两种动态规划思路，然后根据实际问题看看哪种思路容易找到状态转移关系。
 * 另外，找到状态转移和 base case 之后，一定要观察 DP table，看看怎么遍历才能保证通过已计算出来的结果解决新的问题。
 *
 **/

public class DP_SubsequenceSolutionTemplate {

}