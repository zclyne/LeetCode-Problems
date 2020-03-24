// 思路：二分查找
// 根据nums[left]和nums[mid]的关系来判断左半部分是否已经有序
// 再分情况讨论，选择下一个要寻找的区间=，注意nums[left] == nums[mid]的特殊情况
// 此时要避免duplicate，可以简单地将left++，因为nums[left]肯定不是要寻找的target

class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] < nums[mid]) { // left part is sorted
                if (target > nums[mid] || target < nums[left]) { // target should be in the rotated half (right half)
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[left] > nums[mid]) { // left part is rotated
                if (target >= nums[left] || target < nums[mid]) { // target should be in the right half
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) { // tricky here, avoid duplicate
                // since nums[mid] != target, nums[left] != target
                // then we can shrink the left pointer by 1 without affecting the result
                left++;
            }
        }
        return false;
    }
}