import java.util.Arrays;

// 方法：记忆化递归
// 用数组combinationsCount来记录已经访问过的组合的数量
// combinationsCount[i]存储的是target == i时，由nums中的数字可以组成i的combination的总数
// 初始状态下，combinationsCount[0] = 1，表示不选取任何元素就可以构成0
// 其他位置上都为-1，表示还未访问过
// 然后开始回溯法。对于当前的target，如果combinationsCount[target]不等于-1，说明这个target的combination总数已经被计算过
// 不需要重复计算，直接在O(1)时间内返回combinationsCount[target]即可
// 否则，遍历一遍nums，将target - num作为新的target递归计算

class Solution {
    private int[] combinationsCount;

    public int combinationSum4(int[] nums, int target) {
        combinationsCount = new int[target + 1];
        Arrays.fill(combinationsCount, -1);
        combinationsCount[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        if (combinationsCount[target] != -1) {
            return combinationsCount[target];
        }
        int count = 0;
        for (int num : nums) {
            count += helper(nums, target - num);
        }
        combinationsCount[target] = count;
        return count;
    }
}