// 方法：将算法分为两个阶段，crush和drop
// 变量changed记录board是否发生了改变
// crush阶段，遍历board，对于不为0的格子，分别向下、向右尝试crush
// 对应于crushDown和crushRight两个函数。如果某个格子要被crush，就将其值变为负数
// crushDown和crushRight的返回值代表是否需要被crush
// 如果需要被crush，则board还没稳定，changed = true
// 如果changed == true，则进入drop阶段
// 在这一阶段内，将所有的格子移动到board最下方

class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean changed = true;
        while (changed) {
            changed = false;
            // crush candies
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    changed |= crushDown(board, i, j);
                    changed |= crushRight(board, i, j);
                }
            }

            // drop
            if (changed) {
                drop(board);
            }
        }
        return board;
    }

    // return whether board[i][j] need to be crushed
    private boolean crushDown(int[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        int startValue = Math.abs(board[i][j]);
        int count = 0, row = i;
        while (row < m && Math.abs(board[row][j]) == startValue) {
            count++;
            row++;
        }
        if (count >= 3) { // needs to be crushed
            row = i;
            while (count > 0) {
                board[row][j] = board[row][j] > 0 ? -board[row][j] : board[row][j]; // negative means needs to be crushed
                count--;
                row++;
            }
            return true;
        }
        return false;
    }

    // return whether board[i][j] need to be crushed
    private boolean crushRight(int[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        int startValue = Math.abs(board[i][j]);
        int count = 0, col = j;
        while (col < n && Math.abs(board[i][col]) == startValue) {
            count++;
            col++;
        }
        if (count >= 3) { // needs to be crushed
            col = j;
            while (count > 0) {
                board[i][col] = board[i][col] > 0 ? -board[i][col] : board[i][col]; // negative means needs to be crushed
                count--;
                col++;
            }
            return true;
        }
        return false;
    }

    private void drop(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int col = 0; col < n; col++) {
            int row = m - 1, tmp = m - 1;
            while (row >= 0) {
                if (board[row][col] > 0) { // board[row][col] needs to be reserved
                    board[tmp][col] = board[row][col];
                    tmp--;
                }
                row--;
            }
            while (tmp >= 0) {
                board[tmp][col] = 0;
                tmp--;
            }
        }
    }
}