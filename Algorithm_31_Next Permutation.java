// 思路：
// 1. 找到最大的下标k，使得nums[k - 1] < nums[k]
// 2. 若k == 0，说明整个数组已经按照从大到小排列，直接反转
// 若k != 0，说明子数组nums[k ~ nums.length - 1]按照从大到小排列，找到最大的下标l > k，使得nums[l] > nums[k - 1]
// 3. 交换nums[l]、nums[k - 1]
// 4. 将子数组nums[k ~ nums.length - 1]反向

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int k = nums.length - 1;
        while (k > 0 && nums[k] <= nums[k - 1]) {
            k--;
        }
        if (k == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int l = nums.length - 1;
        while (l > k && nums[l] <= nums[k - 1]) {
            l--;
        }
        swap(nums, k - 1, l);
        reverse(nums, k, nums.length - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private void reverse(int[] nums, int i, int j) {
        int left = i, right = j;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}