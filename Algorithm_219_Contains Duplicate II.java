import java.util.HashMap;
import java.util.Map;

// 方法：用hashmap存储每个num上次出现的index

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numToIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numToIdx.containsKey(num) && i - numToIdx.get(num) <= k) {
                return true;
            }
            numToIdx.put(num, i);
        }
        return false;
    }
}