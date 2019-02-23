// Solution 1: Moore Voting Algorithm
// 思路：将两个不同的数相互抵消，最后数组中剩余的就是target
// 变量major记录当前选取的majorityElement，count记录该element的剩余个数
// 初始时设major = nums[0]，count = 1
// 从1开始遍历数组。若当前nums[i] == major，count++，表示个数增加1
// 否则，count--，表示当前major被用掉一个来抵消nums[i]
// 当count减到0时，需要重新选取一个新的major

import java.util.HashMap;
import java.util.Arrays;
class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else {
                count = nums[i] == major ? count + 1 : count - 1;
            }
        }
        return major;
    }
}

// Solution 2: HashMap
class Solution2 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = map.getOrDefault(nums[i], 0);
            if (++count > nums.length / 2) {
                return nums[i];
            }
            map.put(nums[i], count);
        }
        return -1; // would never reach here
    }
}

// Solution 3: Sorting
class Solution3 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}