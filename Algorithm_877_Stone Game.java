// 解法1：DP
// 设dp[i][j]保存的值表示，在只考虑区间[i, j]范围内的所有石头的情况下
// 先手（当前玩家）和后手（下回合玩家）所拥有的石头的数量之差的最大值
// 对于i == j的情况，只有一堆石头，所以dp[i][j] = piles[i]
// 由于只能从左右两端取石头，所以有以下两种情况：
// 1. 从左端取石头，则dp[i][j] = piles[i] - dp[i + 1][j]
// 2. 从右端取石头，则dp[i][j] = piles[j] - dp[i][j - 1]
// 减法是因为回合的交替，这一轮和上一轮的玩家不同
// 需要从二者中取较大的一个作为新的dp[i][j]的值
// 可以优化成一维数组
class Solution {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }
}

// 解法2：数学方法
// 先手必胜
// https://leetcode-cn.com/problems/stone-game/solution/shi-zi-you-xi-by-leetcode-solution/
class MathSolution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}