import java.util.*;

// 思路：直接滑动窗口

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int left = 0, right = 0, res = 0, curSum = 0;
        Set<Integer> set = new HashSet<>();

        while (left < nums.length && right < nums.length) {
            while (right < nums.length && !set.contains(nums[right])) {
                set.add(nums[right]);
                curSum += nums[right];
                res = Math.max(res, curSum);
                right++;
            }
            curSum -= nums[left];
            set.remove(nums[left]);
            left++;
        }

        return res;
    }
}