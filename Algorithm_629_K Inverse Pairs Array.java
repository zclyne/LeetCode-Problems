// 动态规划：https://leetcode.com/problems/k-inverse-pairs-array/discuss/104815/Java-DP-O(nk)-solution

class Solution {
    public static int kInversePairs(int n, int k) {
        int mod = 1000000007;
        // for a given n, the maximum number of inverse pairs occurs when
        // the n numbers are sorted in descending order, where each pair is an inverse pair
        // in this case, the number of pairs = n * (n - 1) / 2, which is the number of inverse pairs
        // so the maximum value of k = n * (n - 1) / 2
        if (k > n * (n - 1) / 2 || k < 0) {
            return 0;
        }
        if (k == 0 || k == n * (n - 1) / 2) {
            return 1;
        }
        long[][] dp = new long[n + 1][k + 1];
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, i * (i - 1) / 2); j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                if (j >= i) {
                    dp[i][j] -= dp[i - 1][j - i];
                }
                dp[i][j] = (dp[i][j] + mod) % mod;
            }
        }
        return (int) dp[n][k];
    }
}