// 思路：首先，易知最终得到的公共子序列一定是s1和s2的最长公共子序列，这是由于只删除1个字符一定好于删除2个字符，例如只删除一个'z'，一定优于删除'aa'
// 因此目标就是找s1与s2的所有最长公共子序列中，所有字符的ascii之和最大的那个。因为保留下来的字符ascii之和越大，说明被删去的字符ascii之和越小
// 按照求两字符串的最长公共子序列的思想，dp[i][j]存放s1[0]~s1[i]与s2[0]~s2[j]的最长公共子序列中，所有字符ascii之和最大的公共子序列的所有字符ascii之和
// 则当s1[i] != s2[j]时，最长子序列的长度一定无法进一步增大，所以dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
// 当s1[i] == s2[j]时，还要考虑最长子序列的长度有可能进一步增大，因此dp[i][j]不仅要考虑max(dp[i - 1][j], dp[i][j - 1])，还要与dp[i - 1][j - 1] + s1[i]比较
// 取三者的最大值
// 最终循环结束后，dp[s1.length() - 1][s2.length() - 1]存放的就是保留下来的子序列的所有字符ascii之和
// 返回值就是s1与s2中所有字符ascii之和减去保留下来的子序列的所有字符ascii乘2

class Solution {
    private int[][] dp;
    public int minimumDeleteSum(String s1, String s2) {
        dp = new int[s1.length()][s2.length()];
        int sumAscii = (int)s1.charAt(0) + (int)s2.charAt(0);
        if (s1.charAt(0) == s2.charAt(0))
            dp[0][0] = (int)s1.charAt(0);
        for (int i = 1; i < s1.length(); i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? Integer.max(dp[i - 1][0], (int)s1.charAt(i)) : dp[i - 1][0];
            sumAscii += (int)s1.charAt(i);
        }
        for (int j = 1; j < s2.length(); j++) {
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? Integer.max(dp[0][j - 1], (int)s1.charAt(0)) : dp[0][j - 1];
            sumAscii += (int)s2.charAt(j);
        }
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = Integer.max(dp[i][j], dp[i - 1][j - 1] + (int)s1.charAt(i));
            }
        }
        return sumAscii - 2 * dp[s1.length() - 1][s2.length() - 1];
    }
}