import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 方法：使用hashmap存储每个元素的出现次数
// 遍历到数组deliciousness中的某个元素时，在哈希表中寻找与当前元素的和等于2的幂的元素个数，然后用当前元素更新哈希表。
// 由于遍历数组时，哈希表中已有的元素的下标一定小于当前元素的下标，因此任意一对元素之和等于2的幂的元素都不会被重复计算。
// 令maxVal表示数组deliciousness中的最大元素，则数组中的任意两个元素之和都不会超过maxVal × 2。令maxSum=maxVal × 2，
// 则任意一顿大餐的美味程度之和为不超过maxSum的某个2的幂

class Solution {
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}