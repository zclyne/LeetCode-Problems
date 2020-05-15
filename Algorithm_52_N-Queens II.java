// 思路：与Algorithm_51_N-Queens相同
// 但是此处不需要记录实际的棋盘形状，只需要记录符合要求的棋盘个数result

class Solution {
    private int result = 0;
    public int totalNQueens(int n) {
        boolean[] colNotAvailable = new boolean[n];
        boolean[] diagonal45NotAvailable = new boolean[2 * n - 1];
        boolean[] diagonal135NotAvailable = new boolean[2 * n - 1];
        backtracking(colNotAvailable, diagonal45NotAvailable, diagonal135NotAvailable, 0, n);
        return result;
    }
    private void backtracking(boolean[] colNotAvailable, boolean[] diagonal45NotAvailable, boolean[] diagonal135NotAvailable, int row, int n) {
        if (row == n) {
            result++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!colNotAvailable[col] && !diagonal45NotAvailable[row + col] && !diagonal135NotAvailable[row - col + n - 1]) {
                // (row, col) is available
                colNotAvailable[col] = true;
                diagonal45NotAvailable[row + col] = true;
                diagonal135NotAvailable[row - col + n - 1] = true;
                backtracking(colNotAvailable, diagonal45NotAvailable, diagonal135NotAvailable, row + 1, n);
                colNotAvailable[col] = false;
                diagonal45NotAvailable[row + col] = false;
                diagonal135NotAvailable[row - col + n - 1] = false;
            }

        }
    }
}