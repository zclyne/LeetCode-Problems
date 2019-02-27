// Solution 1: Iterative DP

// 思路：dp[i]存储用coins中的coin组成i元所需的最少硬币数
// 动态规划的递归关系为：对于coins中的某一coin，若该coin <= i且i - coin可以由coins组成，则dp[i] = 1 + dp[i - coin]
// 初始条件为dp[0] = 0，表示需要0个硬币来组成0元

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) { // can make up i - coin, use coin and i - coin to make up i
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}



// Solution 2: Recursive DP
// 思想相同，递归实现

class Solution2 {

    private int[] dp;

    public int coinChange(int[] coins, int amount) {
        dp = new int[amount + 1];
        return helper(coins, amount);
    }

    private int helper(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        } else if (amount < 0) { // invalid
            return -1;
        } else if (dp[amount] != 0) {
            return dp[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int res = helper(coins, amount - coin);
                if (res != -1) {
                    min = Math.min(min, 1 + res);
                }
            }
        }
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[amount];
    }

}