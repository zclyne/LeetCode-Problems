class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) { // notice >=
            i--;
        }
        // look for the first element in nums[i ~ n - 1] that is greater than nums[i - 1]
        if (i > 0) {
            int j = n - 1;
            while (j > i && nums[j] <= nums[i - 1]) { // notice <=
                j--;
            }
            swap(nums, i - 1, j);
        }
        reverse(nums, i, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}