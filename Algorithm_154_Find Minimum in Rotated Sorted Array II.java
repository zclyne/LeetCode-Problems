// 思路：二分查找，根据nums[mid]和nums[high]之间的三种关系来考虑
// nums[mid] != nums[high]的两种情况都很容易判断，不做考虑
// 这里仅解释nums[mid] == nums[high]时的情况
// 这种情况下，无法确定pivot到底位于左半部分还是右半部分，但可以确定的是
// 由于nums[mid] == nums[high]，所以此时把nums[high]排除到整个要考虑的范围之外
// 不会对结果造成影响，因为nums[mid]可以替代nums[high]
// 所以high--
// 注意mid = low + (high - low) / 2，所以在high == low + 1时
// mid == low，此时如果进入了第二个分支，更新为high == mid == low
// 依然保证了搜索范围是严格缩小的，所以不会出现死循环的情况

class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) { // pivot lies in the right half [mid + 1, high]
                low = mid + 1;
            }
            else if (nums[mid] < nums[high]) { // pviot lies in the left half [low, mid]
                high = mid;
            }
            else { // nums[mid] == nums[high]
                high--;
            }
        }
        
        return nums[low];
    }
}