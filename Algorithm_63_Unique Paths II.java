// 思路：动态规划。dp[i][j]记录走到[i][j]格子上的所有路径数
// 注意初始状态为dp[0][0]，要判断obstacleGrid[0][0]是否为1
// dp[i][j] = dp[i - 1][j] + dp[i][j - 1]，注意i == 0和j == 0
// 可以优化到一维数组

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0; // don't forget to judge whether obstacleGrid[0][0] == 1
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 0) { // available
                    dp[i][j] += j > 0 ? dp[i][j - 1] : 0;
                    dp[i][j] += i > 0 ? dp[i - 1][j] : 0;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}