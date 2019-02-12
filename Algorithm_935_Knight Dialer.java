// 思路：朴素方法，对每一个数字，观察该数字可以由哪些数字跳转得到
// 构造二维数组dp，第一维表示跳数，第二维表示数字0 ~ 9
// 对于第N跳时到达的数字k，dp[i][k] = sum(dp[i - 1][j])，其中j为所有可以到达k的数字
// 特殊情况为5，无法由任何数字跳跃到达，只有在N = 1时为1，否则为0
// 注意每一次做完加法后就要mod 1000000007，否则答案不正确

import java.util.Arrays;
class Solution {
    public int knightDialer(int N) {
        long[][] dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = (dp[i - 1][4] + dp[i - 1][6]) % 1000000007;
                } else if (j == 1) {
                    dp[i][j] = (dp[i - 1][6] + dp[i - 1][8]) % 1000000007;
                } else if (j == 2) {
                    dp[i][j] = (dp[i - 1][7] + dp[i - 1][9]) % 1000000007;
                } else if (j == 3) {
                    dp[i][j] = (dp[i - 1][4] + dp[i - 1][8]) % 1000000007;
                } else if (j == 4) {
                    dp[i][j] = (dp[i - 1][0] + dp[i - 1][3] + dp[i - 1][9]) % 1000000007;
                } else if (j == 5) {
                    dp[i][j] = 0;
                } else if (j == 6) {
                    dp[i][j] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][7]) % 1000000007;
                } else if (j == 7) {
                    dp[i][j] = (dp[i - 1][2] + dp[i - 1][6]) % 1000000007;
                } else if (j == 8) {
                    dp[i][j] = (dp[i - 1][1] + dp[i - 1][3]) % 1000000007;
                } else if (j == 9) {
                    dp[i][j] = (dp[i - 1][2] + dp[i - 1][4]) % 1000000007;
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = sum + dp[N][i];
            sum = sum % 1000000007;
        }
        return (int) sum;
    }
}