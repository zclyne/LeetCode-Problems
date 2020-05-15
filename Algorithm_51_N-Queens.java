// 思路：https://www.youtube.com/watch?v=Xa-yETqFNEQ
// 对对角线做编号。两个方向的对角线条数都是2n - 1条
// 设x和y是当前所在格的横坐标和纵坐标
// 则对于从左下角到右上角方向的对角线，对角线的idx = x + y
// 对于从左上角到右下角方向的对角线，对角线的idx = x - y + n - 1
// 用一个boolean值就可以存储当前的列或对角线是否可以放一个皇后

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[] colNotAvailable = new boolean[n];
        boolean[] diagonal45NotAvailable = new boolean[2 * n - 1];
        boolean[] diagonal135NotAvailable = new boolean[2 * n - 1];
        char[][] curBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(curBoard[i], '.');
        }
        backtracking(result, curBoard, colNotAvailable, diagonal45NotAvailable, diagonal135NotAvailable, 0, n);
        return result;
    }
    private void backtracking(List<List<String>> result, char[][] curBoard, boolean[] colNotAvailable, 
            boolean[] diagonal45NotAvailable, boolean[] diagonal135NotAvailable, int row, int n) {
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                board.add(String.valueOf(curBoard[i]));
            }
            result.add(board);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!colNotAvailable[col] && !diagonal45NotAvailable[row + col] && !diagonal135NotAvailable[row - col + n - 1]) {
                // (row, col) is available
                colNotAvailable[col] = true;
                diagonal45NotAvailable[row + col] = true;
                diagonal135NotAvailable[row - col + n - 1] = true;
                curBoard[row][col] = 'Q';
                backtracking(result, curBoard, colNotAvailable, diagonal45NotAvailable, diagonal135NotAvailable, row + 1, n);
                colNotAvailable[col] = false;
                diagonal45NotAvailable[row + col] = false;
                diagonal135NotAvailable[row - col + n - 1] = false;
                curBoard[row][col] = '.';
            }

        }
    }
}