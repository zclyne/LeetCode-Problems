// 解法1：回溯法
// 用3个set保存当前行、列和box中已经出现过的字符，然后遍历board
// 对于每一个空位，选择出一个可行的数字填入，然后进入下一层递归
// 如果遇到某一个空格无法填入任何数字，返回false
// 如果i == 9，表示整个board都遍历完了，找到了数独的解，返回true
class Solution {
    private char[] numbers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private List<HashSet<Character>> rowExisted = new ArrayList<>();
    private List<HashSet<Character>> colExisted = new ArrayList<>();
    private List<HashSet<Character>> boxExisted = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rowExisted.add(new HashSet<>());
            colExisted.add(new HashSet<>());
            boxExisted.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int box = this.getBox(i, j);
                char c = board[i][j];
                if (c != '.') {
                    rowExisted.get(i).add(c);
                    colExisted.get(j).add(c);
                    boxExisted.get(box).add(c);
                }
            }
        }

        this.backtracking(board, 0, 0);
    }

    private int getBox(int i, int j) {
        return 3 * (i / 3) + (j / 3);
    }

    private boolean backtracking(char[][] board, int i, int j) {
        if (i == 9) { // already traversed the whole board and find the solution
            return true;
        }

        int nextI = 0, nextJ = 0;
        if (j == 8) { // go to the next row
            nextI = i + 1;
            nextJ = 0;
        } else {
            nextI = i;
            nextJ = j + 1;
        }
        
        if (board[i][j] != '.') {
            return this.backtracking(board, nextI, nextJ);
        }

        for (char num : this.numbers) {
            int box = this.getBox(i, j);
            if (this.isValidNumber(num, i, j, box)) {
                this.rowExisted.get(i).add(num);
                this.colExisted.get(j).add(num);
                this.boxExisted.get(box).add(num);
                board[i][j] = num;
                if (this.backtracking(board, nextI, nextJ)) {
                    return true;
                }
                this.rowExisted.get(i).remove(num);
                this.colExisted.get(j).remove(num);
                this.boxExisted.get(box).remove(num);
                board[i][j] = '.';
            }
        }
        return false;
    }

    private boolean isValidNumber(char c, int i, int j, int box) {
        return !this.rowExisted.get(i).contains(c)
            && !this.colExisted.get(j).contains(c)
            && !this.boxExisted.get(box).contains(c);
    }
}

// 解法2：同样是回溯法。对于每一个board[i][j]，若该位置为空，就遍历'1'到'9'
// 看是否存在某一个数是满足条件的。若满足，则把board[i][j]置为该数字之后
// 进入下一层递归。若下一层递归返回true，则表明数独已经被解开
// 直接返回true。若下一层递归返回false，则把board[i][j]恢复成'.'
// 若'1'到'9'全都无法解开当前数独，则当前board不可能有解，直接返回false
// 若把整个board遍历了一遍都没有找到空位，表明当前board已经完全解开，返回true

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // board[i][j] is empty
                if (board[i][j] == '.') {
                    // go through from '1' to '9' and see if it is valid
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    // '1' to '9' are all invalid, return false
                    return false;
                }
            }
        }
        // if there are no empty spots in board, return true
        return true;
    }
    private boolean isValid(char[][] board, int i, int j, char c) {
        // check row
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        // check col
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == c) {
                return false;
            }
        }
        // check box
        int startRow = 3 * (i / 3), startCol = 3 * (j / 3);
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (board[row][col] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}