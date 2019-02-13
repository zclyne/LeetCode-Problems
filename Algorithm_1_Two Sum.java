// 思路：用一个map来存储数组元素与位置信息，键为数组中元素的值，值为该元素在数组中的下标
// 对每一个元素nums[i]，查找target - nums[i]是否存在在map中，若存在，则已经找到答案
// 若不存在，则把(nums[i], i)放入map中

import java.util.HashMap;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}