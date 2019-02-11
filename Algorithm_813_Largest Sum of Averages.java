// 思路：将数组A至多分为K组，等价于将子数组A[startIdx ~ A.length - 1]至多分为K - 1组，再加上A[0 ~ startIdx - 1]
// 二维数组dp的第一维表示子数组起始位置，第二维表示至多可分的group数
// 使用do[i][j]来存储将A[i ~ A.length - 1]这个子数组最多划分为j组的最大平均值之和

import java.util.Arrays;
class Solution {

    double[][] dp;
    double[] sums;

    public double largestSumOfAverages(int[] A, int K) {
        sums = new double[A.length];
        dp = new double[A.length][K + 1]; // start, maxNumOfgroups
        for (int i = 0; i < A.length; i++) { // initialize
            Arrays.fill(dp[i], -1);
            sums[i] = i == 0 ? A[i] : sums[i - 1] + A[i];
        }
        return helper(A, 0, K);
    }

    public double helper(int[] A, int startIdx, int K) {
        if (dp[startIdx][K] != -1) {
            return dp[startIdx][K];
        }
        if (K == 1) {
            return dp[startIdx][1] = (sums[A.length - 1] - sums[startIdx] + A[startIdx]) / (A.length - startIdx);
        } else {
            for (int i = startIdx; i < A.length - K + 1; i++) {
                dp[startIdx][K] = Math.max(dp[startIdx][K],
                                           (sums[i] - sums[startIdx] + A[startIdx]) / (i - startIdx + 1)
                                            + helper(A, i + 1, K - 1));
            }
            return dp[startIdx][K];
        }
    }
}