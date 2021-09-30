import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        int curSum = 0, result = 0;
        preSumMap.put(0, 1);
        for (int num : nums) {
            curSum += num;
            if (preSumMap.containsKey(curSum - k)) {
                result += preSumMap.get(curSum - k);
            }
            preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) + 1);
        }
        return result;
    }
}