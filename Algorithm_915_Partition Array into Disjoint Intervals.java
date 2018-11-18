// 思路：要找到一个分割left和right，使得left中的最大值<=right中的最小值
// 首先对数组A反向遍历，将以A[i]开始、A[A.length - 1]结尾的数组中的最小值存在minimums[i]中
// 然后对数组A正向遍历，将以A[0]开始、A[i]结尾的数组中的最大值存在curMax中
// 并在每次循环中比较curMax与minimums[i + 1]的大小，若满足curMax <= minimums[i + 1]
// 则i + 1是就第一个满足要求的长度

class Solution {
    public int partitionDisjoint(int[] A) {
        int[] minimums = new int[A.length];
        int curMax = Integer.MIN_VALUE;
        minimums[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--)
            minimums[i] = A[i] < minimums[i + 1] ? A[i] : minimums[i + 1];
        for (int i = 0; i < A.length - 1; i++)
        {
            curMax = A[i] > curMax ? A[i] : curMax;
            if (curMax <= minimums[i + 1])
                return i + 1;
        }
        return -1; // will never be executed
    }
}