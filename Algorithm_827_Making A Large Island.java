import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 方法：用三维数组startingPoints记录dfs时的起点，初始状态下，都是-1，表示未访问过
// 然后遍历grid，如果当前格子是1，则进入dfs
// dfs过程中，将所有访问到的并且值为1的格子的startingPoint都设为dfs的起点，表示已经访问过
// dfs的返回值是面积
// 最顶层dfs的返回值就是整个island的面积，以dfs的起点为key，面积为value，存入map。并且实时更新maxArea的值
// 第一遍遍历完成后，再次遍历第二遍
// 若当前grid的值是0，则将其设为1，然后加上其上下左右的相邻格子所在的island的面积
// 注意这里要利用startingPoint来进行去重，否则可能会有重复计算
// 遍历过程中不断更新maxArea的值

class Solution {

    private int[][][] startingPoints;

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        startingPoints = new int[n][n][2];
        Map<String, Integer> areaMap = new HashMap<>();
        int maxArea = 0;

        // initialize startingPoints to all -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                startingPoints[i][j][0] = -1;
                startingPoints[i][j][1] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && startingPoints[i][j][0] == -1 && startingPoints[i][j][1] == -1) {
                    int area = dfs(grid, i, j, n, i, j);
                    String key = pointToStr(i, j);
                    areaMap.put(key, area);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<String> used = new HashSet<>(); // store the starting points that have been used to avoid duplicate computation
                    int curArea = 1;
                    // up
                    if (i > 0 && grid[i - 1][j] == 1) {
                        int[] startingPoint = startingPoints[i - 1][j];
                        curArea += getAreaFromStartingPoint(startingPoint, used, areaMap);
                    }
                    // down
                    if (i < n - 1 && grid[i + 1][j] == 1) {
                        int[] startingPoint = startingPoints[i + 1][j];
                        curArea += getAreaFromStartingPoint(startingPoint, used, areaMap);
                    }
                    // left
                    if (j > 0 && grid[i][j - 1] == 1) {
                        int[] startingPoint = startingPoints[i][j - 1];
                        curArea += getAreaFromStartingPoint(startingPoint, used, areaMap);
                    }
                    // right
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        int[] startingPoint = startingPoints[i][j + 1];
                        curArea += getAreaFromStartingPoint(startingPoint, used, areaMap);
                    }
                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }

        return maxArea;
    }

    private String pointToStr(int x, int y) {
        return x + ", " + y;
    }

    private int getAreaFromStartingPoint(int[] startingPoint, Set<String> used, Map<String, Integer> areaMap) {
        String pointStr = pointToStr(startingPoint[0], startingPoint[1]);
        if (used.contains(pointStr)) {
            return 0;
        }
        used.add(pointStr);
        return areaMap.getOrDefault(pointStr, 0);
    }

    private int dfs(int[][] grid, int i, int j, int n, int startingX, int startingY) {
        if (i < 0 || j < 0 || i == n || j == n || grid[i][j] == 0 || startingPoints[i][j][0] != -1 || startingPoints[i][j][1] != -1) {
            // startingPoints[i][j][0] != -1 || startingPoints[i][j][1] != -1 means grid[i][j] is visited
            return 0;
        }
        startingPoints[i][j][0] = startingX;
        startingPoints[i][j][1] = startingY;
        return 1 + dfs(grid, i - 1, j, n, startingX, startingY)
                 + dfs(grid, i + 1, j, n, startingX, startingY)
                 + dfs(grid, i, j - 1, n, startingX, startingY)
                 + dfs(grid, i, j + 1, n, startingX, startingY);
    }
}