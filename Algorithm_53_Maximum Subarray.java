// 思路：用curSum记录当前子数组中所有元素之和，maxSum记录结果
// 遍历数组，把当前num加到curSum上，并比较curSum和maxSum
// 若curSum小于0，说明之前这一段子数组中所有元素之和小于0，因此一定不包含在之后的子数组中
// 此时，把curSum置0，表示从新的起始位置nums[i + 1]开始计算新的子数组
// 注意要在curSum置0前判断curSum和maxSum的关系。考虑此情况nums = [-1]

class Solution {
    public int maxSubArray(int[] nums) {
        int curSum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }
}