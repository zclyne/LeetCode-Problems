import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    private int[][][] startingPoints;

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        startingPoints = new int[n][n][2];
        Map<String, Integer> areaMap = new HashMap<>();
        int maxArea = 0;

        // initialize startingPoints
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