// 方法：检查以下几个条件
// 1. X的数量要么等于O，要么恰好比O的数量多1
// 2. X和O不可能同时赢
// 3. 若X赢，则X的数量必然比O多1，因为X先手
// 4. 若O赢，则O的数量必然等于X的数量，因为O后手

class Solution {
    public boolean validTicTacToe(String[] board) {
        char[][] charBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                charBoard[i][j] = board[i].charAt(j);
            }
        }
        int countX = countSymbol(charBoard, 'X');
        int countO = countSymbol(charBoard, 'O');
        if (countX < countO || countX > countO + 1) { // invalid number of X's and O's
            return false;
        }
        
        boolean xWins = checkSymbolWins(charBoard, 'X');
        boolean oWins = checkSymbolWins(charBoard, 'O');
        if (xWins && oWins) { // both X and O win
            return false;
        } else if (xWins && countX != countO + 1) {
            return false;
        } else if (oWins && countO != countX) {
            return false;
        }
        return true;
    }

    private int countSymbol(char[][] board, char c) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == c) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean checkSymbolWins(char[][] board, char c) {
        // row
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == c && board[row][1] == c && board[row][2] == c) {
                return true;
            }
        }
        // column
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == c && board[1][col] == c && board[2][col] == c) {
                return true;
            }
        }
        // diagonal
        if (board[0][0] == c && board[1][1] == c && board[2][2] == c ||
            board[0][2] == c && board[1][1] == c && board[2][0] == c) {
            return true;
        }
        return false;
    }
}