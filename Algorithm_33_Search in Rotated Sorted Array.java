// 思路：注意pivot的性质：nums[pivot] < nums[pivot - 1] && nums[pivot] < nums[pivot + 1]
// 特殊情况时pivot == 0或pivot == nums.length - 1
// 可以总结为(pivot == 0 || nums[pivot] < nums[pivot - 1]) && (pivot == nums.length - 1 || nums[pivot] < nums[pivot + 1])
// 使用二分查找的方法来找到pivot，其步骤是不断将nums[pivot]与nums[left]、nums[right]作比较
// 如果nums[pivot] < nums[left]，表明pivot一定在左半边
// 同理，若nums[pivot] > nums[right]，表明pivot一定在右半边
// 通过二分查找找到pivot后，将pivot与target作比较，如果nums[pivot] > target，表明target一定不存在
// 这是因为nums[pivot]是整个nums中最小的元素
// 如果nums[0] <= target，那么target一定在nums[0 ~ pivot - 1]中
// 如果nums[nums.length - 1] >= target，那么target一定在nums[pivot ~ nums.length - 1]中
// 最后再用二分查找找到target

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1, pivot = 0;
        while (left <= right) {
            pivot = (left + right) / 2;
            if ((pivot == 0 || nums[pivot] < nums[pivot - 1]) && (pivot == nums.length - 1 || nums[pivot] < nums[pivot + 1])) { // successfully find the pivot
                break;
            } else if (nums[pivot] > nums[right]) { // pivot is in the right half
                left = pivot + 1;
            } else { // pivot is in the left half
                right = pivot - 1;
            }
        }
        left = 0;
        right = nums.length - 1;
        if (nums[pivot] == target) {
            return pivot;
        } else if (nums[pivot] > target) { // target is less than the minimum element in nums
            return -1;
        } else if (nums[0] <= target) { // target may be in the left half
            right = pivot == 0 ? nums.length - 1 : pivot - 1;
        } else if (nums[nums.length - 1] >= target) { // target may be in the right half
            left = pivot;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}