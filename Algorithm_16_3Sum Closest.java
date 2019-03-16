import java.util.Arrays;

// 思路：类似于Algorithm_15_3Sum，先排序，然后用2个指针，根据当前和的大小来判断要把left++还是right--

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int curMinDiff = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < nums.length - 2; i ++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(curSum - target) < curMinDiff) {
                    res = curSum;
                    curMinDiff = Math.abs(curSum - target);
                }
                if (curSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}