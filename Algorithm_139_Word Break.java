// Solution 1，O(n * m)。其中m为wordDict.size()
// 思路：dp[i]存储s[0, i - 1)能否由wordDict中的word所组成。对wordDict中的每一个word，判断s.substring(i, i + word.length())是否与该word相等
// 若相等，置dp[i + word.length()]为true
// 最后dp[s.length()]即为答案

import java.util.List;
import java.util.HashSet;
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

// Solution 2
// 思路相同，O(n^2)

class Solution2 {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (dp[i] && set.contains(s.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }
        return dp[s.length()];
    }
}