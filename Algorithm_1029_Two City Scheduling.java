// 方法：动态规划
// 二维数组dp[i][j]存储的值表示，在costs中的前i + j - 1人中，选出i个人去city A，j个人去city B时，所需的最少总花费
// 初始状态是为i、j分别为0时，dp[i][0] = dp[i - 1][0] + costs[i - 1][0]
// dp[0][i] = dp[0][i - 1] + costs[i - 1][1]
// 然后嵌套循环遍历，对于当前dp[i][j]，有两种情况：
// 1. 当前的人应飞往city A，则总cost = dp[i - 1][j] + costs[i + j - 1][0]
// 2. 当前的人应飞往city B，则总cost = dp[i][j - 1] + costs[i + j - 1][1]
// 二者取较小值就是dp[i][j]的值

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length / 2;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }
        
        return dp[n][n];
    }
}