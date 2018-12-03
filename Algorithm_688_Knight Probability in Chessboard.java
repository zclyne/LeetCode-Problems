// 思路：dp[i][j][k]表示当前位置为棋盘的[i][j]，剩余可走的步数为k
// 该位置存放的值为此状态下，最终走完所有步数时留在棋盘上的概率
// 某一位置上最终留在棋盘上的概率等于向8个方向上走一步后，新的这8个状态最终留在棋盘上的概率之和再除以8
// 若已经走出棋盘，则最终留在棋盘上的概率为0，若已经走完所有步数，则最终留在棋盘上的概率为1

class Solution {
    private double[][][] dp;
    public double knightProbability(int N, int K, int r, int c) {
        dp = new double[N][N][K + 1];
        return DFS(N, K, r, c);
    }
    public double DFS(int N, int K, int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) // has got off the board
            return 0;
        if (dp[r][c][K] != 0) // has reached this status before
            return dp[r][c][K];
        if (K == 0) // can't go any more
            return 1;
        dp[r][c][K] += DFS(N, K - 1, r + 2, c + 1);
        dp[r][c][K] += DFS(N, K - 1, r + 2, c - 1);
        dp[r][c][K] += DFS(N, K - 1, r - 2, c + 1);
        dp[r][c][K] += DFS(N, K - 1, r - 2, c - 1);
        dp[r][c][K] += DFS(N, K - 1, r + 1, c + 2);
        dp[r][c][K] += DFS(N, K - 1, r + 1, c - 2);
        dp[r][c][K] += DFS(N, K - 1, r - 1, c + 2);
        dp[r][c][K] += DFS(N, K - 1, r - 1, c - 2);
        dp[r][c][K] /= 8;
        return dp[r][c][K];
    }
}