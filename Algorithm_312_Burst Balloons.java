// 方法：动态规划
// dp[i][j]表示戳爆开区间(i, j)内的所有气球时，所能得到的金币数的最大值
// 设i < k < j，k是开区间(i, j)内最后一个被戳爆的气球，那么(i, k)和(k, j)之间的气球已经被全部戳爆了
// 所以dp[i][k]上已经存储了戳爆开区间(i, k)范围内所有气球时，所能够得到的金币数的最大值；dp[k][j]同理
// 所以dp[i][j] = dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]，这就是DP的递推公式
// https://leetcode-cn.com/problems/burst-balloons/solution/zhe-ge-cai-pu-zi-ji-zai-jia-ye-neng-zuo-guan-jian-/

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // 在首位添加一个1，便于处理边界情况
        int[] values = new int[n + 2];
        values[0] = 1;
        values[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }

        int[][] dp = new int[n + 2][n + 2];
        // len表示开区间长度
        for (int len = 3; len <= n + 2; len++) {
            // i表示开区间左端点
            for (int i = 0; i <= n + 2 - len; i++) {
                // j是区间右端点
                int j = i + len - 1;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}