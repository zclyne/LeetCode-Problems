// 思路：记录长度为k的子数组的所有元素之和的最大值，存在maxSum变量中
// 要返回的结果就是maxSum / k，注意要先将其中一个转换为double类型

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curSum = 0;
        for (int i = 0; i < k; i++) {
            curSum += nums[i];
        }
        int maxSum = curSum;
        for (int i = k; i < nums.length; i++) {
            curSum = curSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, curSum);
        }
        return (double) maxSum / k;
    }
}