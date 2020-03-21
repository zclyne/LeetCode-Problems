// 思路：二分查找
// 对于当前数组的中间元素mid，如果nums[mid]大于nums[right]，最小元素必然位于右半部分中
// 反之，最小元素在左半部分中
// 注意while里的判断条件是<，不是<=，另外right = mid而不是mid - 1
// 思考方法：nums应被划分成连续的两个part，2种不同情况下数组长度都应该缩短，不能保持不变
// 由于mid = left + (right - left) / 2，因此mid更靠近left而不是right
// 所以此处第一种情况下left = mid + 1，第二种情况下right = mid
// 能够保证数组长度在每一次迭代中都在缩短

class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) { // min lies in the right half
                left = mid + 1;
            } else { // min lies in the left half
                right = mid;
            }
        }
        return nums[left];
    }
}