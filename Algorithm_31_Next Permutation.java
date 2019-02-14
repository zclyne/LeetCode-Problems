// 思路：
// 1. 找到最大的下标k，使得nums[k - 1] < nums[k]
// 2. 若k == 0，说明整个数组已经按照从大到小排列，直接反转；
// 若k != 0，说明子数组nums[k ~ nums.length - 1]按照从大到小排列，找到最大的下标l > k，使得nums[l] > nums[k - 1]
// 3. 交换nums[l]、nums[k - 1]
// 4. 将子数组nums[k ~ nums.length - 1]反向

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int k = nums.length - 1;
        for (; k > 0; k--) {
            if (nums[k - 1] < nums[k]) {
                break;
            }
        }
        if (k != 0) {
            int l = nums.length - 1;
            for (; l > k - 1; l--) {
                if (nums[l] > nums[k - 1]) {
                    break;
                }
            }
            swap(nums, k - 1, l);
        }
        reverse(nums, k, nums.length - 1);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int start, int end) {
        for (int i = 0; i <= (end - start) / 2; i++) {
            int tmp = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = tmp;
        }
    }
}