// 方法：排序+贪心
// 直觉上认为，需要每次都取尽可能小的和尽可能大的组成一个pair
// 所以先排序，然后从首位逐个取num并组成pair，计算pair sum
// 所有pair sum的最大值就是答案

class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, nums[left] + nums[right]);
            left++;
            right--;
        }
        return result;
    }
}