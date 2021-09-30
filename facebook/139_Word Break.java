import java.util.*;

class Solution {

    private int[] canSegment;

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        canSegment = new int[n];
        Arrays.fill(canSegment, -1); // -1 means not visited yet
        return backtracking(s, 0, dict);
    }

    private boolean backtracking(String s, int cur, Set<String> dict) {
        if (cur > s.length()) {
            return false;
        }
        if (cur == s.length()) {
            return true;
        }
        if (canSegment[cur] != -1) { // visited before
            return canSegment[cur] == 1;
        }

        boolean result = false;
        for (String word : dict) {
            if (cur + word.length() > s.length()) { // too long
                continue;
            }
            if (s.substring(cur, cur + word.length()).equals(word)) {
                if (backtracking(s, cur + word.length(), dict)) {
                    canSegment[cur] = 1;
                    return true;
                }
            }
        }
        canSegment[cur] = 0; // cannot segment
        return false;
    }
}

// DP solution

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            if (dp[i]) {
                for (String word : wordDict) {
                    int end = i + word.length();
                    if (end > s.length()) {
                        continue;
                    }
                    if (s.substring(i, end).equals(word)) {
                        dp[end] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }
}