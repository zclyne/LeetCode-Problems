// Solution 1: O(n)
// 思路：类似于Algorithm_142

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}


// Solution 2: O(nlogn)
// 思路：类似Binary Search。初始时left = 1, right = n
// 选取一个mid = (left + right) / 2，遍历数组nums，找出所有<=mid的数字的数量，存入变量count
// 如果count > mid，根据Pigeonhole Principle，重复元素一定出现在[left, mid]中，把right设为mid并继续循环
// 否则，重复元素一定出现在[mid + 1, right]中，把left设为mid + 1并继续循环
// 循环终止条件是left == right，此时只有一个数字，即要寻找的重复元素

class Solution2 {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int count = 0, mid = (left + right) / 2;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}