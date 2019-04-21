// 思路：先把整个数组反转，再对前k个元素和后len - k个元素做一遍反转

class Solution {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = 0; start + i < end - i; i++) {
            int tmp = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = tmp;
        }
    }

}