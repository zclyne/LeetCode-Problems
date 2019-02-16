// 思路：动态规划。dp[i][j]存储走到[i][j]格子所拥有的unique path总数
// dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
// 注意i == 0和j == 0的边界情况
// 空间复杂度可以从O(m * n)优化到O(m)

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}