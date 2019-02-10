// 思路：https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116664/Schematic-explanation-of-two-equivalent-DP-recurrence-formula/233676

class Solution {
    public int numTilings(int N) {
        long[] dp = new long[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % 1000000007;
        }
        return (int) dp[N];
    }
}