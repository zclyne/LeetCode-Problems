// 思路：dp[j]用于存储在A中以i - 1结尾、在B中以j - 1结尾的repeated subarray的长度
// 对两数组遍历，若发现A[i - 1] == B[j - 1]，则有dp[j] = dp[j - 1] + 1
// 为防止先前修改的内容对后面的计算造成影响，引入临时数组tmpRes，在每次对j遍历完成后再将tmpRes赋值给dp

class Solution {
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int[] dp = new int[B.length + 1];
        int maxLen = 0;
        for (int i = 1; i <= A.length; i++)
        {
            int[] tmpRes = new int[A.length + 1];
            for (int j = 1; j <= B.length; j++)
            {
                if (A[i - 1] == B[j - 1]) tmpRes[j] = dp[j - 1] + 1;
                maxLen = Integer.max(maxLen, tmpRes[j]);
            }
            dp = tmpRes;
        }
        return maxLen;
    }
}