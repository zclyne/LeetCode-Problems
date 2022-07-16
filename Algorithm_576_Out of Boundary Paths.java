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

// 方法2：迭代DP

class Solution2 {
    private int modulo = 1000000007;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        int[][][] dp = new int[m][n][maxMove + 1];
        // initialization
        // left and right columns
        for (int i = 0; i < m; i++) {
            dp[i][0][1] += 1;
            dp[i][n - 1][1] += 1;
        }
        // top and bottom rows
        for (int j = 0; j < n; j++) {
            dp[0][j][1] += 1;
            dp[m - 1][j][1] += 1;
        }
        
        for (int move = 2; move <= maxMove; move++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) {
                        dp[i][j][move] = (dp[i][j][move] + dp[i - 1][j][move - 1]) % modulo;
                    }
                    if (i < m - 1) {
                        dp[i][j][move] = (dp[i][j][move] + dp[i + 1][j][move - 1]) % modulo;
                    }
                    if (j > 0) {
                        dp[i][j][move] = (dp[i][j][move] + dp[i][j - 1][move - 1]) % modulo;
                    }
                    if (j < n - 1) {
                        dp[i][j][move] = (dp[i][j][move] + dp[i][j + 1][move - 1]) % modulo;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= maxMove; i++) {
            result = (result + dp[startRow][startColumn][i]) % modulo;
        }
        return result;
    }
}
