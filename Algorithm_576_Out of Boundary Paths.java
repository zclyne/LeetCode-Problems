// 思路：dp[i][j][k]存储的值是开始位置为[i][j]，且剩余步数为k时走出grid的不同路径数量
// 注意存储时每做一次加法，就要对1000000000 + 7取模

class Solution {
    private long[][][] dp;
    private boolean[][][] visited;
    public int findPaths(int m, int n, int N, int i, int j) {
        dp = new long[m][n][N + 1];
        visited = new boolean[m][n][N + 1];
        return (int)(DFS(m, n, N, i, j) % (1000000000 + 7));
    }
    public long DFS(int m, int n, int N, int i, int j) {
        if ( i < 0 || i == m || j < 0 || j == n) return 1; // have already got out of the grid
        if (N == 0) return 0;
        if (dp[i][j][N] == 0 && !visited[i][j][N]) {
            dp[i][j][N] = (dp[i][j][N] + DFS(m, n, N - 1, i - 1, j)) % (1000000000 + 7);
            dp[i][j][N] = (dp[i][j][N] + DFS(m, n, N - 1, i + 1, j)) % (1000000000 + 7);
            dp[i][j][N] = (dp[i][j][N] + DFS(m, n, N - 1, i, j - 1)) % (1000000000 + 7);
            dp[i][j][N] = (dp[i][j][N] + DFS(m, n, N - 1, i, j + 1)) % (1000000000 + 7);
            visited[i][j][N] = true;
        }
        return dp[i][j][N];
    }
}