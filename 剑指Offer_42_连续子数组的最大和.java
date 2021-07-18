// 方法：用curSum存储当前的子数组所有元素和
// 注意max的初始值应该被设为Integer.MIN_VALUE，而不能是0
// 否则无法处理nums中只包含一个负数的情况，例如nums = [-1]

class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum < 0) {
                curSum = 0;
            }
            curSum += nums[i];
            max = Math.max(max, curSum);
        }
        return max;
    }
}