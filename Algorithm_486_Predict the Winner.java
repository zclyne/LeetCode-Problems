// 思路：构造数组dp[nums.length][nums.length]，dp[i][j]表示某玩家从nums[i]到nums[j]（都包括）这个子数组中比另一个玩家多得到的点数大小
// 设玩家P1选择了nums[i]，那么P2可以从nums[i + 1]~nums[j]这个子数组中获得比P1多dp[i + 1][j]的点数大小，所以这种选择下，P1总共可以得到比P2多nums[i] - dp[i + 1][j]点数
// 同理，若P1选择了nums[j]，那么P2可以从nums[i]~nums[j - 1]这个子数组中获得比P1多dp[i][j - 1]的点数大小，这种选择下，P1总共可以得到比P2多nums[j] - dp[i][j - 1]点数
// dp数组的初始状态在对角线上，显然dp[i][i] = nums[i]
// 最后，从对角线开始构建数组，最终结果为dp[0][n - 1]。这一状态就是整个数组，其对应的先选择的玩家是Player 1，若改位置上的值大于等于0，说明Player 1获胜

class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // len starts from 2 because len == 1 is just the initialization for loop
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                dp[left][right] = Integer.max(nums[left] - dp[left + 1][right], nums[right] - dp[left][right - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}