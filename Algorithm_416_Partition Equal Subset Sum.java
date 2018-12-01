// 思路：本题核心思想即判断是否能从数组中找出一些数，使得这些数的和等于所有数之和的1/2
// 若所有数之和为奇数，则显然不存在这样的partition，返回false
// 否则，建立数组dp[nums.length + 1][halfSum + 1]，dp[i][j]表示能否从前i个数中找到一些数使得这些数之和为j（i从1开始数）
// 因此有两种情况：
// 1. 不选择nums中的第i个数，此时dp[i][j] = dp[i - 1][j]，因为不选择nums[i - 1]时，所有数之和不受影响。
// 2. 选择nums中的第i个数，此时dp[i][j] = dp[i - 1][j - nums[i - 1]]，因为要从j中扣除已选择的nums[i - 1]
// 所以dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]
// 最后返回dp[nums.length][halfSum]，即是否能从数组中选出一些数使得这些数的和等于halfSum
// 代码中将二维数组优化为了一维数组，注意内层循环必须降序，否则先前循环的修改将影响之后的循环结果
// 还要注意只有j >= nums[i]时，才会存在以上两种不同情况

class Solution {
    public boolean canPartition(int[] nums) {
        int halfSum = 0;
        for (int num : nums) halfSum += num;
        if (halfSum % 2 == 1) return false; // sum is odd, cannot partition
        halfSum /= 2;
        boolean[] dp = new boolean[halfSum + 1];
        Arrays.fill(dp, false);
        dp[0] = true; // base case
        for (int i = 0; i < nums.length; i++) {
            for (int j = halfSum; j >= nums[i]; j--) { // pay attention here
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[halfSum];
    }
}