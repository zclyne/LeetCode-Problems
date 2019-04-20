import java.util.List;
import java.util.ArrayList;

// 思路：isPalindrome[i][j]记录s的子串s[i, j]是否回文
// 在DFS中，pos记录当前的开始位置，cur记录已经选用的所有回文子串
// 若pos == s.length()，说明已经到达结尾，直接把此时的cur加入res中
// 从pos开始遍历s，若s[pos, i]是回文，就把它加入cur中，并向下递归
// 递归退出后，把cur中的最后一个元素删除

class Solution {

    private boolean[][] isPalindrome;
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        isPalindrome = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = j - i <= 2 || isPalindrome[i + 1][j - 1];
                }
            }
        }
        DFS(s, 0, new ArrayList<>());
        return res;
    }

    private void DFS(String s, int pos, List<String> cur) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (isPalindrome[pos][i]) {
                cur.add(s.substring(pos, i + 1));
                DFS(s, i + 1, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

}