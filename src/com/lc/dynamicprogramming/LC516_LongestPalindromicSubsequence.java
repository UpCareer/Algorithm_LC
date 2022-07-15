package com.lc.dynamicprogramming;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 */

public class LC516_LongestPalindromicSubsequence {

    /**
     * dp 数组的定义是：在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j]。
     *
     * 状态转移方程：
     *
     * Copy
     * if (s[i] == s[j])
     *     // 它俩一定在最长回文子序列中
     *     dp[i][j] = dp[i + 1][j - 1] + 2;
     * else
     *     // s[i+1..j] 和 s[i..j-1] 谁的回文子序列更长？
     *     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp 数组全部初始化为 0
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }

}
