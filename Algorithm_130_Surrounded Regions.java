// 思路：先从四条边界上做DFS，把所有与边界相连的'O'标记成'A'
// 然后遍历board，把所有的'A'变回'O'，把所有的'O'变为'X'

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        for (int col = 0; col < board[0].length; col++) { // top border and bottom border
            mark(board, 0, col);
            mark(board, board.length - 1, col);
        }
        for (int row = 0; row < board.length; row++) { // left border and right border
            mark(board, row, 0);
            mark(board, row, board[0].length - 1);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }
    private void mark(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O' ) return;
        board[i][j] = 'A';
        mark(board, i - 1, j);
        mark(board, i + 1, j);
        mark(board, i, j - 1);
        mark(board, i, j + 1);
    }
}