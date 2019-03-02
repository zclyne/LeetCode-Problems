// my result思路：HashMap dp的键为一个和，值为使用nums[0]~nums[i]来组成这个和的路径数
// 遍历nums，维护dp，对dp中的每一个键值对，对键加或减nums[i]得到一个新的和
// 若该和不在tempDp中，则将tempDp中该位置置为curValue，否则加上curValue
// 最终dp.get(S)即使用整个nums数组来组成S的路径数量。若S不是dp的键，表明不存在这样的路径，返回0

// better result思路：本问题等价为将原数组分为两个子数组P和N，使得sum(P) - sum(N) = target
// 则sum(P) + sum(N) + sum(P) - sum(N) = sum(nums) + target
// 2 * sum(P) = sum(nums) + target
// sum(P) = (sum(nums) + target) / 2
// 问题退化为Algorithm_416

import java.util.HashMap;
import java.util.Map;
// my result, using HashMap
class MySolution {
    public int findTargetSumWays(int[] nums, int S) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            HashMap<Integer, Integer> tempDp = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int curKey = (Integer) entry.getKey();
                int curValue = (Integer) entry.getValue();
                tempDp.put(curKey + nums[i], tempDp.getOrDefault(curKey + nums[i], 0) + curValue);
                tempDp.put(curKey - nums[i], tempDp.getOrDefault(curKey - nums[i], 0) + curValue);
            }
            dp = tempDp;
        }
        return dp.getOrDefault(S, 0);
    }
}

// better result
class Solution {
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 != 0 ? 0 : subsetSum(nums, (s + sum) / 2);
    }
    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = s; j >= nums[i]; j--) { // must be in reverse order
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[s];
    } 
}