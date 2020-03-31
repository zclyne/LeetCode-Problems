// 思路：
// 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
// 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
// 3, If p.charAt(j) == '*': 
//    here are two sub conditions:
//                1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
//                2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
//                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
//                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
//                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty

// https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
// 下的第一条评论可读性更好

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // to match empty string, p should be "#*#*#*#*...", therefore
        // we only need to consider the chars with even index (if index starts from 1)
        for (int j = 2; j < n + 1; j += 2) {
            dp[0][j] = p.charAt(j - 1) == '*' ? dp[0][j - 2] : false;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    // exactly match
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) != '.' && p.charAt(j - 2) != s.charAt(i - 1)) {
                        // not match, p.charAt(j - 2) and p.charAt(j - 1) behave like
                        // they do not exist
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // dp[i][j - 2] stands for 'a*' matches 0 char
                        // dp[i - 1][j - 2] stands for 'a*' matches 1 char
                        // dp[i - 1][j] stands for 'a*' matches multiple chars
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}