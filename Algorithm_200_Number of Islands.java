// 思路：直接DFS。数组visited非必需，可以直接在grid上修改，visited[i][j] = true 等价为grid[i][j] = '0'

class Solution {
    private boolean[][] visited;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    mark(grid, i, j);
                }
            }
        }
        return count;
    }
    public void mark(char[][]grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || visited[i][j] || grid[i][j] != '1') {
            return;
        }
        visited[i][j] = true;
        mark(grid, i - 1, j);
        mark(grid, i + 1, j);
        mark(grid, i, j - 1);
        mark(grid, i, j + 1);
    }
}