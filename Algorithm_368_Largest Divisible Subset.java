import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// 方法：DP
// 根据题目的描述，如果一个所有元素互不相同的集合中的任意元素存在整除关系，就称为整除子集
// 为了得到「最大整除子集」，我们需要考虑如何从一个小的整除子集扩充成为更大的整除子集
// 首先需要观察到以下两个性质：
// 1. 如果整数a是整除子集S中的最小元素b的约数，即b % a == 0，则可以将a添加到这个整除子集S中，得到一个更大的整除子集
// 2. 如果整数c是整除子集S中的最大元素d的倍数，即c % d == 0，则可以将c添加到这个整除子集S中，得到一个更大的整除子集
// 已知这两个性质后，可以得到DP的思路：
// 首先将nums从小到大排列。并定义动态规划数组dp，dp[i]表示，在选择nums[i]作为整除子集中的最大元素时，整个整除子集的大小的最大值
// 设nums的长度为n，则初始状态是dp[i] = 1, 0 <= i < n
// 遍历nums，对于当前的nums[i]，找到一个j，使得j满足0 <= j < i，并且nums[i] % nums[j] == 0
// 则可以把nums[i]加入到以nums[j]为最大元素的整除子集中，也就是dp[i] = dp[j] + 1。这里利用了上面的第二条性质
// 要取所有满足条件的dp[i]的最大值，所以dp[i] = Math.max(dp[i], dp[j] + 1), for j in [0, i)
// 遍历完成后，dp数组中的最大值就是nums的整除子集的大小的最大值maxSize
// 然后反向遍历dp，找到下标i使得dp[i] == maxSize，将此时的nums[i]加入结果集result，记minVal = nums[i]，代表当前整除子集中的最小元素值
// 然后maxSize--，继续向前遍历dp，找到下标j使得dp[j] = maxSize，并且minVal % nums[j] == 0，此时可以把nums[j]加入结果集result，这里利用了上面第一条性质
// 直到最终maxSize == 0，所有数都已经被加入到结果中

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxSize = Math.max(maxSize, dp[i]);
        }

        List<Integer> result = new ArrayList<>();
        Integer minVal = null; // minVal is the minimum element in the subset
        int i = n - 1;
        while (maxSize > 0) {
            if (dp[i] == maxSize && (minVal == null || minVal % nums[i] == 0)) {
                result.add(nums[i]);
                minVal = nums[i];
                maxSize--;
            }
            i--;
        }
        return result;
    }
}