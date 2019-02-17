// 思路：回溯法。数组visited记录board[i][j]在本轮中是否已经被使用
// curIdx记录当前应匹配的字符在word中的下标
// 如果curIdx == word.length()，则已经匹配完毕，返回true
// 否则，首先判断下标i、j是否在合法的范围内，还要判断board[i][j]是否已经被使用或者board[i][j]不等于word[curIdx]
// 有以上任何一种情况出现时，都要返回false
// 最后，对上、下、左、右四个方向递归调用backtracking()，只要有一个方向返回true，整个函数就返回true
// 若4个方向都为false，则返回false

class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtracking(board, visited, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtracking(char[][] board, boolean[][] visited, String word, int curIdx, int i, int j) {
        if (curIdx == word.length()) { // match
            return true;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || visited[i][j] || board[i][j] != word.charAt(curIdx)) {
            return false;
        }
        visited[i][j] = true;
        boolean result =  backtracking(board, visited, word, curIdx + 1, i - 1, j)
                       || backtracking(board, visited, word, curIdx + 1, i + 1, j)
                       || backtracking(board, visited, word, curIdx + 1, i, j - 1)
                       || backtracking(board, visited, word, curIdx + 1, i, j + 1);
        visited[i][j] = false;
        return result;
    }
}