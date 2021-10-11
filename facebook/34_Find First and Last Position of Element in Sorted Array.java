// binary search
// use left close, right open interval

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = binarySearch(nums, target);
        if (left == n || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = binarySearch(nums, target + 1);
        return new int[]{left, right - 1};
    }

    private int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}