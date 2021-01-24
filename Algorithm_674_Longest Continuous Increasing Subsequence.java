// 思路：直接从左到右遍历，注意for循环外部还需要判断一下result和curLen的关系
// 这对应于最长递增子序列正好以原数组的最后一个元素结束的情况，这种情况下
// 不会进入for循环内部的else分支

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 1, lastNum = nums[0], curLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lastNum) {
                curLen++;
            } else {
                result = Math.max(result, curLen);
                curLen = 1;
            }
            lastNum = nums[i];
        }
        result = Math.max(result, curLen);
        return result;
    }
}