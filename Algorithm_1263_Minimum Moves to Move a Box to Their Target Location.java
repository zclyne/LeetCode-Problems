import java.util.*;

// 方法：双重BFS
// 外层BFS寻找box的新的可能的位置
// 内存BFS在canReach方法中，用于判断player能否从当前位置到达预期的位置

class Solution {

    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}};

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] playerPosition = new int[2];
        int[] targetPosition = new int[2];
        int[] boxPosition = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                } else if (grid[i][j] == 'B') {
                    boxPosition[0] = i;
                    boxPosition[1] = j;
                } else if (grid[i][j] == 'T') {
                    targetPosition[0] = i;
                    targetPosition[1] = j;
                }
            }
        }

        Set<String> visited = new HashSet<>();
        // queue stores the current position of the box, the current position of the player, and the number of pushes
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{boxPosition[0], boxPosition[1], playerPosition[0], playerPosition[1], 0});
        visited.add(positionToString(boxPosition[0], boxPosition[1], playerPosition[0], playerPosition[1]));

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();
            int curBoxX = curInfo[0], curBoxY = curInfo[1];
            int curPlayerX = curInfo[2], curPlayerY = curInfo[3];
            int curNumPush = curInfo[4];
            if (curBoxX == targetPosition[0] && curBoxY == targetPosition[1]) { // reach the target
                return curNumPush;
            }
            for (int[] direction : this.directions) {
                int newBoxX = curBoxX + direction[0], newBoxY = curBoxY + direction[1];
                int newPlayerX = curBoxX - direction[0], newPlayerY = curBoxY - direction[1];
                // use -1 as the box coordinate because we don't need to check it here
                if (isValid(grid, newBoxX, newBoxY, -1, -1)
                    && !visited.contains(positionToString(newBoxX, newBoxY, newPlayerX, newPlayerY))
                    && canReach(grid, new int[]{curPlayerX, curPlayerY}, new int[]{newPlayerX, newPlayerY}, new int[]{curBoxX, curBoxY})) {
                    queue.offer(new int[]{newBoxX, newBoxY, newPlayerX, newPlayerY, curNumPush + 1});
                    visited.add(positionToString(newBoxX, newBoxY, newPlayerX, newPlayerY));
                }
            }
        }
        return -1;
    }

    // returns whether the player can get to the destination
    private boolean canReach(char[][] grid, int[] playerPosition, int[] destination, int[] boxPosition) {
        int m = grid.length, n = grid[0].length;
        int destX = destination[0], destY = destination[1];
        int boxX = boxPosition[0], boxY = boxPosition[1];
        if (destX < 0 || destX == m || destY < 0 || destY == n) {
            return false;
        }
        if (grid[destX][destY] == '#') {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(playerPosition);
        visited.add(positionToString(playerPosition[0], playerPosition[1]));

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int curX = curPosition[0], curY = curPosition[1];
            if (curX == destX && curY == destY) {
                return true;
            }
            for (int[] direction : this.directions) {
                int newX = curX + direction[0], newY = curY + direction[1];
                if (isValid(grid, newX, newY, boxX, boxY)
                    && !visited.contains(positionToString(newX, newY))) {
                    queue.offer(new int[]{newX, newY});
                    visited.add(positionToString(newX, newY));
                }
            }
        }
        return false;
    }

    private boolean isValid(char[][]grid, int x, int y, int boxX, int boxY) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x == m || y < 0 || y == n) {
            return false;
        }
        if (grid[x][y] == '#') {
            return false;
        }
        if (x == boxX && y == boxY) {
            return false;
        }
        return true;
    }

    private String positionToString(int x, int y) {
        return x + ", " + y;
    }
    private String positionToString(int x1, int y1, int x2, int y2) {
        return x1 + ", " + y1 + ", " + x2 + ", " + y2;
    }
}