// 思路：观察得到，第i位上的值只有可能取i或i + 1
// 若为i，则一定可行，直接进入下一轮迭代
// 若为i + 1，则要保证第i + 1位上的值为i，否则就会出现A[i] > A[j] 且 j > i + 1的情况
// 这种情况不符合题目要求

class Solution {
    public boolean isIdealPermutation(int[] A) {
        int len = A.length;
        for (int i = 0; i < len - 1; i++)
        {
            if (A[i] == i) continue; // always valid
            else if (A[i] == i + 1)
            {
                if (A[i + 1] == i) i++; // valid，jump to the (i + 2)th iteration
                else return false;
            }
            else return false;
        }
        return true;
    }
}