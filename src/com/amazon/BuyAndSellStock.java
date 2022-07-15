package com.amazon;

public class BuyAndSellStock {
    /*
        dp[-1][...][0] = 0
        解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0。

        dp[-1][...][1] = -infinity
        解释：还没开始的时候，是不可能持有股票的。
        因为我们的算法要求一个最大值，所以初始值设为一个最小值，方便取最大值。

        dp[...][0][0] = 0
        解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0。

        dp[...][0][1] = -infinity
        解释：不允许交易的情况下，是不可能持有股票的。
        因为我们的算法要求一个最大值，所以初始值设为一个最小值，方便取最大值。
     */

    /* dp[i][k][0 or 1]
            0 <= i <= n - 1, 1 <= k <= K
        n 为天数，大 K 为交易数的上限，0 和 1 代表是否持有股票。
        此问题共 n × K × 2 种状态，全部穷举就能搞定。

            for 0 <= i < n:
            for 1 <= k <= K:
            for s in {0, 1}:
        dp[i][k][s] = max(buy, sell, rest) */

    /*
      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
                    max( 今天选择 rest,        今天选择 sell       )
     */

    /*
      dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                    max( 今天选择 rest,         今天选择 buy         )
     */

    /*
        base case：
    dp[-1][...][0] = dp[...][0][0] = 0
    dp[-1][...][1] = dp[...][0][1] = -infinity

    状态转移方程：
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */

}
