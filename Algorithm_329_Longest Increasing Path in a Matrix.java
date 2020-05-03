// 思路：DFS。dp[i][j]记录以matrix[i][j]为起点的最长的递增序列的长度
// dp[i][j] == 0表示当前节点还没有被访问过

class Solution {

    private int[][] dp;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        dp = new int[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, DFS(matrix, i, j));
            }
        }
        return result;
    }

    private int DFS(int[][] matrix, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int maxLength = 1;
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            maxLength = Math.max(maxLength, 1 + DFS(matrix, i - 1, j));
        }
        if (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) {
            maxLength = Math.max(maxLength, 1 + DFS(matrix, i + 1, j));
        }
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            maxLength = Math.max(maxLength, 1 + DFS(matrix, i, j - 1));
        }
        if (j < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j]) {
            maxLength = Math.max(maxLength, 1 + DFS(matrix, i, j + 1));
        }
        dp[i][j] = maxLength;
        return dp[i][j];
    }
}