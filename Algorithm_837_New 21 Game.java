// 思路：数组dp[i]表示到达点数i的概率
// 在i < K时，这个概率等于前W个概率之和除以W，这是因为取1~W的可能性相同
// 在i > K时，任何>=K的点数都不需要算在概率之和中，因为这些情况下游戏已经结束
// 最终，数组中dp[K] ~ dp[N]的所有数字之和即答案

class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N - K >= W - 1) {
            return 1;
        }
        double[] dp = new double[N + 1];
        dp[0] = 1;
        double sum = 1, res = 0;
        for (int i = 1; i < N + 1; i++) {
            dp[i] = sum / W;
            if (i < K) {
                sum += dp[i];
            } else { // if i is already >= K, we don't need to add dp[i] to sum because Alice has stopped drawing numbers
                res += dp[i];
            }
            if (i - W >= 0) {
                sum -= dp[i - W];
            }
        }
        return res;
    }
}