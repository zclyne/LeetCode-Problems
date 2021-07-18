// 方法：使用二分查找，分别找到target的最左下标和target + 1的最左下标

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        // look for the leftMost target
        int leftMost = binarySearch(nums, target);
        if (leftMost == nums.length || nums[leftMost] != target) { // target not found
            return 0;
        }
        // look for the rightMost target, equivalent to looking for the leftMost target + 1
        int rightMost = binarySearch(nums, target + 1) - 1;

        return rightMost - leftMost + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] >= target
                right = mid - 1;
            }
        }
        return left;
    }
}