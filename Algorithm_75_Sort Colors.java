// 思路：遇到0时，就把0交换到nums的头部；遇到2时，就把2交换到nums的尾部
// 注意判断i与idx_0、idx_2的关系

class Solution {
    public void sortColors(int[] nums) {
        int idx_0 = 0, idx_2 = nums.length - 1, i = 0;
        while (i <= idx_2) {
            if (nums[i] == 0 && i > idx_0) {
                swap(nums, i, idx_0++);
            } else if (nums[i] == 2 && i < idx_2) {
                swap(nums, i, idx_2--);
            } else {
                i++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}