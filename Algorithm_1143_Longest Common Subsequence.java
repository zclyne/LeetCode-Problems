// 方法：动态规划
// dp[i][j]存储的是text1.substring(0, i)与text2.substring(0, j)能够组成的LCS的长度

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] text1Arr = text1.toCharArray(), text2Arr = text2.toCharArray();
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (text1Arr[i - 1] == text2Arr[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[m][n];
    }
}