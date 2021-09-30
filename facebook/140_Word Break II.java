import java.util.*;

class Solution {

    private List<String> result;
    private Set<String> dict;

    public List<String> wordBreak(String s, List<String> wordDict) {
        result = new ArrayList<>();
        dict = new HashSet<>(wordDict);
        backtracking(s, 0, "");
        return result;
    }

    private void backtracking(String s, int cur, String curString) {
        if (cur == s.length()) {
            result.add(curString.substring(1)); // remove the first space
            return;
        }
        for (String word : this.dict) {
            if (cur + word.length() > s.length()) { // too long
                continue;
            }
            if (s.substring(cur, cur + word.length()).equals(word)) {
                backtracking(s, cur + word.length(), curString + " " + word);
            }
        }
    }
}