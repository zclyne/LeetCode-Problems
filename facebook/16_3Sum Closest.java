import java.util.Arrays;

// sort and two pointers

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum == target) { // can't be even closer
                    return target;
                }
                int curDiff = Math.abs(target - curSum);
                if (curDiff < minDiff) {
                    minDiff = curDiff;
                    result = curSum;
                }
                if (curSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}