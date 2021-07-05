import java.util.ArrayList;
import java.util.List;

// 方法1：回溯法+前缀树
// 对words中的每个单词构建前缀树，然后对board进行遍历
// 在前缀树中查找是否构成单词

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new LinkedList<>();
        if (board.length == 0 || board[0].length == 0 || words.length == 0) {
            return ans;
        }
        //构建字典树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        //进行dfs
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(ans, board, trie, "", i, j);
            }
        }
        return ans;
    }

    private void dfs(List<String> ans, char[][] board, Trie trie, String str, int x, int y) {
        if (!legal(board, x, y) || trie == null) {
            return;
        }
        char ch = board[x][y];
        if (ch == '.') {
            return;
        }
        str += ch;
        trie = trie.getArr()[ch - 'a'];
        if (trie != null && trie.getEnd()) {
            ans.add(str);
            //防止一个单词被重复添加
            trie.setEnd(false);
        }

        board[x][y] = '.';
        dfs(ans, board, trie, str, x - 1, y);
        dfs(ans, board, trie, str, x + 1, y);
        dfs(ans, board, trie, str, x, y - 1);
        dfs(ans, board, trie, str, x, y + 1);
        board[x][y] = ch;
    }

    private boolean legal(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}


//实现字典树
class Trie {

    private Trie[] arr;

    private boolean end;

    Trie() {
        arr = new Trie[26];
    }

    public void insert(String word) {
        if (word == null || "".equals(word)) {
            return;
        }
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (trie.arr[index] == null) {
                trie.arr[index] = new Trie();
            }
            trie = trie.arr[index];
        }
        trie.end = true;
    }

    public boolean getEnd() {
        return end;
    }

    public Trie[] getArr() {
        return arr;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}



// 方法2：回溯法，比较慢
// 对于已经访问过的board中的元素，将其置为'.'，等下一层递归调用结束后，再修改回原来的值

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (findWord(board, word)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean findWord(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtracking(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) { // successfully found the word
            return true;
        }
        // index out of bound or board[i][j] have already been visited
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == '.') {
            return false;
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '.';
        boolean result = backtracking(board, word, i - 1, j, k + 1)
               || backtracking(board, word, i + 1, j, k + 1)
               || backtracking(board, word, i, j - 1, k + 1)
               || backtracking(board, word, i, j + 1, k + 1);
        board[i][j] = c;
        return result;
    }
}