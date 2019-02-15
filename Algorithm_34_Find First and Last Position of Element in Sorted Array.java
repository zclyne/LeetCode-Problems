// 思路：先使用二分查找找到target在原数组中的一个位置，然后线性向左右两边扩展
// 用start和end来记录target的起始下标和终止下标，最后返回{start, end}
// 若未找到，返回{-1, -1}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                int start = mid, end = mid;
                while (start > 0 && nums[start - 1] == nums[mid]) {
                    start--;
                }
                while (end < nums.length - 1 && nums[end + 1] == nums[mid]) {
                    end++;
                }
                return new int[] {start, end};
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[] {-1, -1};
    }
}