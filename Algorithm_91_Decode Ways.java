// 思路：dp[i]保存s的前i个字符可以decode为多少种情况
// 则对于当前的i，有两种情况：
// 1. s.charAt(i)看作单个字符decode，且s.charAt(i) != '0'，则dp[i + 1] += dp[i]
// 2. s.charAt(i)和s.charAt(i - 1)看作整体，s.charAt(i - 1) != '0'，并且整体小于等于26，则dp[i + 1] += dp[i - 1]
// 注意在第二种情况中，判断.scharAt(i - 1)是否为'0'是很重要的，因为形如'01'的字符串是不能decode的
// 空间复杂度可以优化到O(1)

class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') { // s.charAt(i) can be decoded as a single char
                dp[i + 1] += dp[i];
            }
            if (i > 0 && s.charAt(i - 1) != '0') {
                int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
                if (num <= 26) { // s.charAt(i) can be decoded together with s.charAt(i - 1)
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        return dp[s.length()];
    }
}