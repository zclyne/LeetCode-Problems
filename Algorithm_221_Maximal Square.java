// 思路：dp[i][j]记录以matrix[i - 1][j - 1]为右下角的最大正方形的边长
// 为求dp[i][j]，要考虑左、上、对角线三个方向上的dp值，并取最小值+1为新的正方形边长
// 可以优化到一维数组

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int maxLen = 0;
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }
}