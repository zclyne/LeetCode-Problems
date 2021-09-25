import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() > 1) { // can pair with itself
                    result++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) { // consider num + k only, so that there will not be any duplicate computation
                    result++;
                }
            }
        }
        return result;
    }
}