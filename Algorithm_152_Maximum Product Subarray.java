// 思路：变量curMax和变量curMin分别存储以当前nums[i]结尾的subarray中所能得到的最大乘积和最小乘积
// 若当前nums[i] >= 0，新的最大值可能等于nums[i]，也可能等于原本的最大值*nums[i]
// 若当前nums[i] < 0，则新的最大值可能等于nums[i]，也可能等于原本的最小值*nums[i]，因为负负得正
// 对于最小值，同理

class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0], curMax = nums[0], curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int newMax, newMin;
            if (nums[i] >= 0) {
                newMax = Math.max(curMax * nums[i], nums[i]);
                newMin = Math.min(curMin * nums[i], nums[i]);
            } else {
                newMax = Math.max(curMin * nums[i], nums[i]);
                newMin = Math.min(curMax * nums[i], nums[i]);
            }
            curMax = newMax;
            curMin = newMin;
            res = Math.max(res, curMax);
        }
        return res;
    }
}