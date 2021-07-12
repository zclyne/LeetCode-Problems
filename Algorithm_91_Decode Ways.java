// 思路：动态规划
// dp[i]保存s的前i个字符可以decode为多少种情况
// 设当前下标为i，若s.substring(i, i + 1)能够被解码，则dp[i] += dp[i - 1]
// 若s.substring(i - 1, i + 1)能够被解码，则dp[i] += dp[i - 2]。特殊情况是i == 1时，dp[i - 2]不存在，用1代替
// 空间复杂度可以优化到O(1)

class Solution {
    public int numDecodings(String s) {
        if (s.startsWith("0")) { // it's impossible to decode a string starting with 0
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (this.canDecode(s.substring(i, i + 1))) {
                dp[i] += dp[i - 1];
            }
            if (i >= 1 && this.canDecode(s.substring(i - 1, i + 1))) {
                dp[i] += i == 1 ? 1 : dp[i - 2];
            }
        }
        return dp[s.length() - 1];
    }

    private boolean canDecode(String s) {
        if (s.startsWith("0")) {
            return false;
        }
        int result = Integer.parseInt(s);
        return result <= 26;
    }
}