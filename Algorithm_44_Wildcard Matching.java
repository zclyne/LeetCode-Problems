// Better Solution，最坏情况下的时间复杂度仍为O(mn)，但是整体上比DP解法更快
// 思路：s和p记录当前再字符串str和pattern中的位置，match记录上一个star所匹配到的最后一个str中的字符的下标
// starIdx记录pattern中的上一个'*'的下标
// 第一个if对应于pattern[p] == str[s]，或pattern[p] == '?'的情况，此时把s和p都 + 1
// 第二个if对应于pattern[p] == '*'的情况，先假定该'*'匹配0个str中的字符，因此直接把p++，并令match = s, starIdx = p
// 第三个if对应于pattern[p]和str[s]无法匹配，并且pattern中之前出现过'*'，则把p退回到这个'*'的位置，并用这个'*'多匹配一个字符
// 因此p = starIdx + 1, match++, s = match
// 最后一种情况对应于pattern[p]和str[s]无法匹配，并且pattern中之前没有出现过'*'，因此匹配失败
// 最后，若pattern尾部还有'*'，则把p不断++
// 若瑞中p == pattern.length()，表明可以用pattern来匹配str，返回true，否则返回false

class Solution {
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else {
                return false;
            }
        }
        
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }
        
        return p == pattern.length();
    }
}

// My DP Solution
// 思路：dp[i][j] = true表示s的前i个字符能用p的前j个字符来match成功
// base case为dp[0][0] = true

class DPSolution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                // if j == 0, dp[i][j] must be false except for i == 0
                if (j > 0) {
                    // can match one char
                    if (i > 0 && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    // can match any char
                    if (p.charAt(j - 1) == '*') {
                        // dp[i][j - 1] means '*' matches 0 char
                        dp[i][j] = dp[i][j - 1];
                        if (i > 0) {
                            // dp[i - 1][j] means '*' matches more than 1 char
                            // dp[i - 1][j - 1] means '*' matches exactly 1 char
                            dp[i][j] |= dp[i - 1][j] | dp[i - 1][j - 1];
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}