import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 思路：与Algorithm_15_3Sum相同，先排序，然后保持i、j不变，移动left和right
// 注意消除重复

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { // avoid duplicate for nums[j]
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) { // too large
                    break;
                }
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) { // too small
                    continue;
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left + 1] == nums[left]) { // avoid duplicate for nums[left]
                            left++;
                        }
                        while (left < right && nums[right - 1] == nums[right]) { // avoid duplicate for nums[right]
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            while (i < nums.length - 4 && nums[i + 1] == nums[i]) { // avoid duplicate for nums[i]
                i++;
            }
        }
        return res;
    }

}