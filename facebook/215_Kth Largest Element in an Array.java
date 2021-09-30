class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int pivot = nums[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (nums[j] <= pivot) {
                    swap(nums, ++i, j);
                }
            }
            swap(nums, ++i, right);
            if (i == n - k) {
                return nums[i];
            } else if (i < n - k) {
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return nums[left];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}