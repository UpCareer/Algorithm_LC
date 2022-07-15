package com.amazon;

public class LC121_BestTimetoBuyandSelltock {
    public int maxProfit(int[] prices) {
/*        // 原始版本
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n - 1][0];*/
       /* int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;*/

        if(prices == null || prices.length <= 1) return 0;
        int min = prices[0];
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            // Firstly, update min then get max
            if(prices[i] < min){
                min = prices[i];
            }

            res = Math.max(res, prices[i] - min);
        }

        return res;
    }
}
