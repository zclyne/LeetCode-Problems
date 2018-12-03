// 思路：maxLens[i]存放以nums[i]结尾的所有递增subsequence中的最长subsequence的长度
// counts[i]存放以nums[i]结尾的递增subsequence中具有长度为maxLens[i]的subsequence的个数

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[] maxLens = new int[nums.length];
        int[] counts = new int[nums.length];
        int res = 0, curMaxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLens[i] = counts[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (maxLens[j] == maxLens[i] - 1)
                        counts[i] += counts[j];
                    else if (maxLens[j] > maxLens[i] - 1) {
                        maxLens[i] = maxLens[j] + 1;
                        counts[i] = counts[j];
                    }
                }
            }
            if (maxLens[i] == curMaxLen)
                res += counts[i];
            else if (maxLens[i] > curMaxLen) {
                curMaxLen = maxLens[i];
                res = counts[i];
            }
        }
        return res;
    }
}