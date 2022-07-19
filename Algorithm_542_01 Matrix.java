import java.util.*;

// 方法1：BFS
// 从所有0开始，由于BFS的距离最短的最先被处理的特性，当一个1被第一次访问到时，当时的距离必然是最短的

class Solution {
    int[] DIR = new int[]{0, 1, 0, -1, 0};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length; // The distance of cells is up to (M+N)
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; ++r)
            for (int c = 0; c < n; ++c)
                if (mat[r][c] == 0) {
                    q.offer(new int[]{r, c});
                } else {
                    mat[r][c] = -1; // Marked as not processed yet!
                }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) { // process all four neighbors
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr < 0 || nr == m || nc < 0 || nc == n || mat[nr][nc] != -1) {
                    continue;
                }
                mat[nr][nc] = mat[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        return mat;
    }
}

// 方法2：DP
// 先从上到下、从左到右遍历mat，并且对于每个1，只考虑其上方和左边的两个neighbor
// 再从下到上、从右到左遍历mat，并且对于每个1，只考虑其下方和右边的两个neighbor

class Solution2 {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length, INF = m + n; // The distance of cells is up to (M+N)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) continue;
                int top = INF, left = INF;
                if (r - 1 >= 0) top = mat[r - 1][c];
                if (c - 1 >= 0) left = mat[r][c - 1];
                mat[r][c] = Math.min(top, left) + 1;
            }
        }
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (mat[r][c] == 0) continue;
                int bottom = INF, right = INF;
                if (r + 1 < m) bottom = mat[r + 1][c];
                if (c + 1 < n) right = mat[r][c + 1];
                mat[r][c] = Math.min(mat[r][c], Math.min(bottom, right) + 1);
            }
        }
        return mat;
    }
}