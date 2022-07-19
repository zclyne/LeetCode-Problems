import java.util.*;

// 直接用回溯法，对digit，直接append然后递归
// 对letter，分别转换成大写和小写，然后递归

class Solution {
    private List<String> result;
    public List<String> letterCasePermutation(String s) {
        this.result = new ArrayList<>();
        backtracking(s.toCharArray(), 0, "");
        return this.result;
    }
    private void backtracking(char[] s, int idx, String cur) {
        if (idx == s.length) {
            this.result.add(cur);
            return;
        }
        if (Character.isDigit(s[idx])) {
            backtracking(s, idx + 1, cur + s[idx]);
        } else {
            backtracking(s, idx + 1, cur + Character.toLowerCase(s[idx]));
            backtracking(s, idx + 1, cur + Character.toUpperCase(s[idx]));
        }
    }
}