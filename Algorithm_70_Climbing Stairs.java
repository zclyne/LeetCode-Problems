// 思路：直接动态规划，dp[i] = dp[i - 1] + dp[i - 2]
// 此处优化为O(1)空间复杂度

class Solution {
    public int climbStairs(int n) {
        int last_1 = 1, last_2 = 1, cur = 1;
        for (int i = 2; i <= n; i++) {
            cur = last_1 + last_2;
            last_2 = last_1;
            last_1 = cur;
        }
        return cur;
    }
}