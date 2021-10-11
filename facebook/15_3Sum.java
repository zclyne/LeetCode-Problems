// sort and two pointers
// don't forget to deal with duplicates

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    left++;
                }
                while (left < right && left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && right < n - 1 && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }

        return result;
    }
}