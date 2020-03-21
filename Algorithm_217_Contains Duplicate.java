import java.util.Arrays;
import java.util.HashSet;

// Solution 1
// 思路：直接比较nums[i]、nums[j]是否相等

class Solution1 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }
}

// Solution 2
// 思路：排序后看是否存在相等的相邻两元素

class Solution2 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}

// Solution 3
// 思路：用set存下所有已经出现过的数字

class Solution3 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return true; // insertion failed, indicates that there is a duplicate
        }
        return false;
    }
}

// Solution 4
// 思路：直接用Java 8的stream
class Solution4 {
    public boolean containsDuplicate(int[] nums) {
        return nums.length != Arrays.stream(nums)
                .distinct()
                .count();
    }
}