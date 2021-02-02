// 思路：动态规划
// 与普通动态规划不同的是，因为这道题要求的是初始时所需要的最少的健康点数
// 所以采用反向遍历的方法，先假设到达目的地时恰好剩余的健康点数为1，然后反向
// 递推，得到初始时的健康点数
// dp[i][j]存储的是进入dungeon[i][j]这个格子时，所需要的最少的健康点数
// 如果从当前格子能够向右走，且右边格子所需的健康点数至少为dp[i][j + 1]
// 那么当前格子所需要的健康点数即为Math.max(1, dp[i][j + 1] - dungeon[i][j])
// 1对应于dp[i][j + 1] - dungeon[i][j] <= 0的情况，这说明在dungeon[i][j]这个格子里
// 能补充的健康点数大于等于进入右边格子所需要的健康点数，而骑士的健康点数至少为1
// 对于走到下方的格子，同理，当前格子所需要的健康点数为Math.max(1, dp[i + 1][j] - dungeon[i][j])
// 取2种情况中值较小的，就是进入dungeon[i][j]格子所最少需要的健康点数
// 最终dp[0][0]就是要求的答案

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // dp[i][j] stores the minimum health point that knight requires to get to dungeon[i][j]
        int[][] dp = new int[m][n];
        // when the knight finally reach the bottom-right grid, he should have at least 1 health point
        // so the minimum health point to get to the final grid should be:
        // 1, if dungeon[m - 1][n - 1] >= 0
        // 1 - dungeon[m - 1][n - 1], if dungeon[m - 1][n - 1] < 0
        // which is, in summary, Math.max(1, 1 - dungeon[m - 1][n - 1])
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                if (j < n - 1) { // can go to the right grid
                    int rightHealth = Math.max(1, dp[i][j + 1] - dungeon[i][j]);
                    dp[i][j] = Math.min(dp[i][j], rightHealth);
                }
                if (i < m - 1) { // can go to the bottom grid
                    int bottomHealth = Math.max(1, dp[i + 1][j] - dungeon[i][j]);
                    dp[i][j] = Math.min(dp[i][j], bottomHealth);
                }
            }
        }

        return dp[0][0];
    }
}