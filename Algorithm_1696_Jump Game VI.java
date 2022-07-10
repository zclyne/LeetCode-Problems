import java.util.*;

// 用长度为k的滑动窗口维护dp数组的长度为k的子数组中的最大元素值
// dp数组当前元素值就等于这个滑动窗口中的最大值加上nums[i]
// 滑动窗口覆盖的下标范围是[i - k, i - 1]
// O(1)时间复杂度维护最大值的方法见第239题，用deque
// https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();

        // maintain a sliding window of length k
        dp[0] = nums[0];
        deque.offerLast(0);
        for (int i = 1; i < n; i++) {
            // remove out-of-range elements
            if (deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            // update max score for the current index
            dp[i] = nums[i] + dp[deque.peekFirst()];
            // remove elements smaller than the current one
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}