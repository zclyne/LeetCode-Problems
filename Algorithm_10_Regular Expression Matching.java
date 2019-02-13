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

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 2; i < p.length() + 1; i++) { // get rid of the prefix like c* when matching "aab" with "c*a*b"
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2]; // * will never show up alone, so i - 2
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) { // s[i] and p[j] match
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') { // p[j] and s[i] do not match
                        dp[i + 1][j + 1] = dp[i + 1][j - 1]; // use p[j - 2] to match s[i]
                    } else { // three different situations in 3.2 above
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}