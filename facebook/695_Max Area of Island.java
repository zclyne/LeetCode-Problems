// 方法：DFS
// 对于已经访问过的格子，将其值从1改为0，表示已访问过

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, DFS(grid, i, j, m, n));
            }
        }
        return maxArea;
    }

    private int DFS(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i == m || j < 0 || j == n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0; // mark as visited
        return 1 + DFS(grid, i - 1, j, m, n)
                 + DFS(grid, i + 1, j, m, n)
                 + DFS(grid, i, j - 1, m, n)
                 + DFS(grid, i, j + 1, m, n);
    }
}