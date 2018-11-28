// 思路：构造数组dp[m + 1][n + 1]，最外层对strs中的每一个str做循环，每次循环统计出该str中0和1的个数，记为count_0和count_1
// dp[p][q]表示使用p个0和q个1，可以构成的从str[0]到str[i]这些字符串中的最大字符串个数
// 更新dp数组时，注意要从右下角更新到左上角，因为新的dp[p][q]更新时会用到上一轮最外层循环的结果dp[p - count_0][q - count_1]
// dp[p][q]有两种取值，在不选择组成strs[i]时，dp[p][q]保持不变
// 若选择组成strs[i]，则dp[p][q] = 1 + dp[p - count_0][q - count_1]，+1表示当前选择的strs[i]

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int count_0 = 0, count_1 = 0;
        for (int i = 0; i < strs.length; i++) {
            count_0 = count_1 = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') count_0++;
                else count_1++;
            }
            for (int p = m; p >= count_0; p--) {
                for (int q = n; q >= count_1; q--) {
                    dp[p][q] = Integer.max(dp[p][q], 1 + dp[p - count_0][q - count_1]);
                }
            }
        }
        return dp[m][n];
    }
}