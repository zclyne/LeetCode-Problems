import java.util.ArrayList;

// 思路：直接按顺序遍历原数组

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int left = 0;
        while (left < nums.length) {
            int right = left + 1;
            while (right < nums.length && nums[right] == nums[right - 1] + 1) {
                right++;
            }
            if (right == left + 1) {
                result.add(String.valueOf(nums[left]));
            } else {
                result.add(String.valueOf(nums[left]) + "->" + String.valueOf(nums[right - 1]));
            }
            left = right;
        }
        return result;
    }
}