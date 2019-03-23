import java.util.HashSet;

// My Solution
// 思路：直接遍历board，遍历过程中通过行和列的index计算出当前格子属于哪个3 * 3的正方形
// 然后判断board[i][j]是否在当前行、当前列或当前正方形中出现过
// 若出现过，则该数独invalid，返回false
// 遍历正常完成后，返回true
// 可以通过bit manipulation优化为一维数组

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] squares = new boolean[9][10];
        // check rows and columns
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int curNum = board[i][j] - '0';
                    int curSquare = 3 * (i / 3) + (j / 3);
                    if (rows[i][curNum] || cols[j][curNum] || squares[curSquare][curNum]) {
                        return false;
                    } else {
                        rows[i][curNum] = true;
                        cols[j][curNum] = true;
                        squares[curSquare][curNum] = true;
                    }
                }
            }
        }
        return true;
    }
}



// String Solution
// 把数字连同其所在的行、列和正方形信息存入一个set，若存放失败，则说明出现了重复，返回false

class StringSolution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in column " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }
}