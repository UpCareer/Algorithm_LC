package com.lc.dynamicprogramming;

/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * In one step, you can delete exactly one character in either string.
 *
 * Example 1:
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 *
 * Example 2:
 *
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 */

public class LC583_DeleteOperationforTwoStrings {

    /**
     * 函数签名如下：
     *
     * int minDistance(String s1, String s2);
     * 题目让我们计算将两个字符串变得相同的最少删除次数，那我们可以思考一下，最后这两个字符串会被删成什么样子？
     *
     * 删除的结果不就是它俩的最长公共子序列嘛！
     *
     * 那么，要计算删除的次数，就可以通过最长公共子序列的长度推导出来：
     *
     * int minDistance(String s1, String s2) {
     *     int m = s1.length(), n = s2.length();
     *     // 复用前文计算 lcs 长度的函数
     *     int lcs = longestCommonSubsequence(s1, s2);
     *     return m - lcs + n - lcs;
     * }
     * 这道题就解决了！
     */
}
