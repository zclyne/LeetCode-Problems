// DFS。用数组timeToRotten记录每个orange变rotten所需的最短时间
// 每次DFS从一个rotten orange出发，尝试将其周围的orange也变rotten
// 把每个已访问过的新鲜orange暂时置为2，表示visited，dfs结束后再改回来

import java.util.*;

class Solution {

    private int[][] timeToRotten;

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        this.timeToRotten = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(this.timeToRotten[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) { // dfs starting from the current rotten orange
                    this.dfs(grid, i, j, 0);
                }
            }
        }
        int minRottenTime = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.timeToRotten[i][j] == Integer.MAX_VALUE && grid[i][j] == 1) { // is fresh orange but cannot get rotten
                    return -1;
                }
                if (this.timeToRotten[i][j] != Integer.MAX_VALUE) {
                    minRottenTime = Math.max(minRottenTime, this.timeToRotten[i][j]);
                }
            }
        }
        return minRottenTime;
    }

    private void dfs(int[][] grid, int i, int j, int curTime) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i == m || j < 0 || j == n) {
            return;
        }
        if (grid[i][j] == 0 || grid[i][j] == 2 && curTime > 0) { // originally rotten or visited, except for the starting point
            return;
        }
        // now, grid[i][j] = 1 for all grids except for the starting point
        // the following check is important, otherwise TLE
        if (this.timeToRotten[i][j] != Integer.MAX_VALUE && curTime >= this.timeToRotten[i][j]) { // rotten by another orange with less time
            return;
        }
        this.timeToRotten[i][j] = Math.min(this.timeToRotten[i][j], curTime);
        grid[i][j] = 2; // mark as visited
        curTime++;
        dfs(grid, i - 1, j, curTime);
        dfs(grid, i + 1, j, curTime);
        dfs(grid, i, j - 1, curTime);
        dfs(grid, i, j + 1, curTime);
        if (curTime > 0) { // do not change the starting point
            grid[i][j] = 1;
        }
    }
}


// BFS solution

class Solution2 {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for(int i = 0 ; i < rows ; i++) {
            for(int j = 0 ; j < cols ; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i , j});
                }
                else if(grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0 
        if(count_fresh == 0) return 0;
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        //bfs starting from initially rotten oranges
        while(!queue.isEmpty()) {
            ++count;
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                int[] point = queue.poll();
                for(int dir[] : dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //if x or y is out of bound
                    //or the orange at (x , y) is already rotten
                    //or the cell at (x , y) is empty
                        //we do nothing
                    if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    //mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    //put the new rotten orange at (x , y) in queue
                    queue.offer(new int[]{x , y});
                    //decrease the count of fresh oranges by 1
                    count_fresh--;
                }
            }
        }
        return count_fresh == 0 ? count-1 : -1;
    }
}