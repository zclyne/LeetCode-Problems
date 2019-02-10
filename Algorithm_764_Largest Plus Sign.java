// 思路：对每个格子grid[i][j]，若该格子为0，则直接置0
// 否则，找该格子上下左右四个方向上的order的最小值，即为该格子的order值
// 初始化时，将所有不在mines中的格子上的值都置为N，方便以后用Math.min()
// 嵌套循环中，分别对左、右、上、下方向找order，如果新得到的order小于grid[i][j]，则要进行更新
// 如果遇到当前grid[i][j]为0，说明这个格子在mines中，计数器要进行重置
// 嵌套循环结束后，每一个grid[i][j]上存储的都是这个格子上的order最小值
// 找所有格子的order的最大值返回

class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) { // initialize
            for (int j = 0; j < N; j++) {
                grid[i][j] = N;
            }
        }
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            int l = 0, r = 0, u = 0, d = 0;
            for (int j = 0; j < N; j++) { // left
                l = grid[i][j] == 0 ? 0 : l + 1; // reset left
                grid[i][j] = Math.min(grid[i][j], l);
            }
            for (int k = N - 1; k >= 0; k--) { // right
                r = grid[i][k] == 0 ? 0 : r + 1; // reset right
                grid[i][k] = Math.min(grid[i][k], r);
            }
            for (int j = 0; j < N; j++) { // up
                u = grid[j][i] == 0 ? 0 : u + 1; // reset up
                grid[j][i] = Math.min(grid[j][i], u);
            }
            for (int k = N - 1; k >= 0; k--) { // down
                d = grid[k][i] == 0 ? 0 : d + 1; // reset down
                grid[k][i] = Math.min(grid[k][i], d);
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) { // look for the maximum
            for (int j = 0; j < N; j++) {
                res = Math.max(grid[i][j], res);
            }
        }
        return res;
    }
}