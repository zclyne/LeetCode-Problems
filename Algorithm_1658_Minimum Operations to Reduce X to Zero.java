import java.util.*;

// 思路：滑动窗口
// 从两头删除的元素之和为x，等价于保留一个subarray，其所有元素之和等于total - x
// 其中，total是nums所有元素之和
// 注意特殊情况total - x = 0

class Solution {
    public int minOperations(int[] nums, int x) {
        int left = 0, right = 0, res = Integer.MAX_VALUE;
        int total = 0, targetSum = 0, curSum = 0;
        
        // calculate the total sum of all elements in nums
        // and then calculate the target sum of the subarray
        // which is total sum - x
        for (int num : nums) {
            total += num;
        }
        targetSum = total - x;
        if (targetSum == 0) { // special case, can remove all elements
            res = nums.length;
        }

        while (left < nums.length && right < nums.length) {
            while (right < nums.length && curSum + nums[right] <= targetSum) {
                curSum += nums[right];
                if (curSum == targetSum) {
                    // the length of operations equals the length of
                    // the entire nums array minus the length of the 
                    // subarray reserved
                    int curLen = right - left + 1;
                    res = Math.min(nums.length - curLen, res);
                }
                right++;
            }
            curSum -= nums[left];
            left++;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}