// 思路：记数组中所有元素之和为totalSum，则对于某个制定的下标i
// 左边所有元素之和可以在遍历过程中累加得到，记为leftSum
// 右边所有元素之和为totalSum - leftSum - nums[i]，记为rightSum
// 判断leftSum和rightSum的关系即可

class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int leftSum = 0, rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            rightSum = totalSum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}