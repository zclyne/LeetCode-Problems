// 思路：动态规划
// dp[i + 1][j + 1]表示s[0 : i + 1]的distinct subsequence中与t[0 : j + 1]相等的subsequence的个数（左开右闭）
// 对于dp[i + 1][j + 1]，考虑两种情况
// 1. s[i] != t[j]，则不能用s[i]来匹配t[j]，只能从s[0 : i]中找字符来匹配t[j]，所以dp[i + 1][j + 1] = dp[i][j + 1]
// 2. s[i] == t[j]，则又可分为以下两种情况
// 2.1 选用s[i]来匹配t[j]，则dp[i + 1][j + 1] = dp[i][j]
// 2.2 不选用s[i]来匹配t[j]，则dp[i + 1][j + 1] = dp[i][j + 1]
// 所以dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1]
// 初始状态是dp[i][0] = 1，表示任意s[0 : i]都可以匹配一次空字符串
// dp[0][j] = 0，表示空字符串无法匹配任何t[0][j]
// 最终时dp[s.length()][t.length()]就是答案

class Solution {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}