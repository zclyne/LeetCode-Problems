// 思路：由于原board中每个元素只有第一个bit被使用，要么为1，要么为0
// 因此可以使用每个元素的第二个bit来存储下一次的状态
// 遍历结束后，再把每个位置上的元素都右移一位，就完成了更新

class Solution {

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += nextState(board, i, j) << 1; // shift left by 1 bit and add it to board[i][j] to store the next state within the 2nd bit
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    // return the next state of board[i][j], 1 means live and 0 means dead
    private int nextState(int[][] board, int i, int j) {
        int numLiveNeighbors = countLiveNeighbors(board, i, j);
        if (board[i][j] == 1) { // board[i][j] is a live cell
            return numLiveNeighbors < 2 || numLiveNeighbors > 3 ? 0 : 1; // an live cell with < 2 neighbors or > 3 neighbors become a dead cell
        } else {
            return numLiveNeighbors == 3 ? 1 : 0; // an dead cell with 3 live neighbors become a live cell
        }
    }

    // return the number of live cells around board[i][j]
    private int countLiveNeighbors(int[][] board, int i, int j) {
        return liveOrNot(board, i - 1, j - 1) + liveOrNot(board, i - 1, j) + liveOrNot(board, i - 1, j + 1)
             + liveOrNot(board, i, j - 1) + liveOrNot(board, i, j + 1)
             + liveOrNot(board, i + 1, j - 1) + liveOrNot(board, i + 1, j) + liveOrNot(board, i + 1, j + 1);
            
    }

    // return whether the cell board[i][j] is live now
    private int liveOrNot(int[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return 0;
        }
        return board[i][j] & 1; // must & 1 here, because the 2nd bit of board[i][j] may be 1
    }

}