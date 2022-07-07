// 从最后一行开始自底向上构建球的最终结果
// below代表向当前行的下一行的每一列放球时对应的结果
// cur代表向当前行的每一列放球时对应的结果

class Solution {
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] cur = new int[n];
        int[] below = null;

        // for each row, decide how the ball would go for each column
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // redirect the ball to the right
                    if (j == n - 1 || grid[i][j + 1] == -1) {
                        // the ball gets stuck now
                        cur[j] = -1;
                    } else {
                        cur[j] = below == null ? j + 1 : below[j + 1];
                    }
                } else { // redirect the ball to the left
                    if (j == 0 || grid[i][j - 1] == 1) {
                        // the ball gets stuck now
                        cur[j] = -1;
                    } else {
                        cur[j] = below == null ? j - 1 : below[j - 1];
                    }
                }
            }
            below = cur;
            cur = new int[n];
        }

        return below;
    }
}