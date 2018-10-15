// 思路：将每一个已经访问过的元素置为-1，代表已访问
// 注意本题中，一个S[i]中的所有元素对应的S长度都相同，因为是环状结构
class Solution {
    public int arrayNesting(int[] nums) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++)
        {
            int tmpIdx = i, curSize = 0;
            while (nums[tmpIdx] != -1)
            {
                int lastIdx = tmpIdx;
                tmpIdx = nums[tmpIdx];
                nums[lastIdx] = -1;
                curSize++;
            }
            maxLen = Integer.max(maxLen, curSize);
        }
        return maxLen;
    }
}