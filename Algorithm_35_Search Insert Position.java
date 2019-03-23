// 思路：直接用二分查找，注意while条件要加等号
// 在二分查找的过程中，target的index始终在范围[left, right + 1]内
// 当target <= nums[left]时，为left，当target > nums[right]时，要插入在right + 1位置上
// 因此最终循环退出后，由于left == right + 1，所以index在范围[left, left]内
// 所以直接返回left

class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) { // should be <=, not just <
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left; // now, left == right + 1
    }
}