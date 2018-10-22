// 思路：从0开始，到下标为i的subarray可以sort后满足要求的条件是前i + 1个元素中的最大值为i
// 由于最大值为i，所以其他i位一定都是小于i的数，保证了该subarray排序后结果为0, 1, 2, ..., i
// 对于之后的数组思想也相同

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int maxNum = -1, res = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > maxNum) maxNum = arr[i];
            if (i == maxNum) res++;
        }
        return res;
    }
}