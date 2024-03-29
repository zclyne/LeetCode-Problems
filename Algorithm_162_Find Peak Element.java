// 思路：用二分查找寻找子数组中的local maximum
// 因为子数组中的最大值一定是原数组的一个满足条件的peak

// solution 1: recursive
class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[len - 1] > nums[len - 2]) return len - 1;
        return binarySearch(nums, 0, len - 1);
    }
    private int binarySearch(int[] nums, int low, int high)
    {
        if (low == high) return low;
        int mid = (low + high) / 2;
        if (nums[mid] < nums[mid + 1]) return binarySearch(nums, mid + 1, high);
        else return binarySearch(nums, low, mid);
    }
}

// solution 2: iterative
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < nums[mid+1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}