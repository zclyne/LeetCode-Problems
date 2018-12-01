// 思路：dp[i][j]表示以s[i]开始、s[j]结束的这段字符串内最长回文subsequence的长度
// 初始状态为dp[i][i] = 1
// 维护dp时，start从最后一个字符s[s.length() - 1]开始递减，最终start = 0
// 存在两种情况：1. s[start] == s[end] 2. s[start] != s[end]
// 对第一种情况，查dp[start + 1][end - 1]，由于外层循环中start递减，因此该位置上保存的值即为s[start + 1] ~ s[end - 1]这段字符串中的最长回文subsequence长度
// + 2表示首尾这两个相同字符在原来的最长回文subsequence的基础上长度加2
// 对第二种情况，从dp[start][end - 1]和dp[start + 1][end]中取较大值即可
// 最终dp[0][s.length() - 1]即为答案

class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) dp[i][i] = 1;
        for (int start = s.length() - 1; start >= 0; start--) {
            for (int end = start + 1; end < s.length(); end++) {
                if (s.charAt(start) == s.charAt(end))
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                else
                    dp[start][end] = Integer.max(dp[start + 1][end], dp[start][end - 1]);
            }
        }
        return dp[0][s.length() - 1];
    }
}

// 2D array -> 1D array
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[] dp = new int[s.length()];
        for (int start = s.length() - 1; start >= 0; start--) {
            int[] tempDp = new int[s.length()];
            for (int end = start; end < s.length(); end++) {
                if (start == end)
                    tempDp[end] = 1;
                else if (s.charAt(start) == s.charAt(end))
                    tempDp[end] = dp[end - 1] + 2;
                else
                    tempDp[end] = Integer.max(dp[end], tempDp[end - 1]);
            }
            dp = tempDp;
        }
        return dp[s.length() - 1];
    }
}