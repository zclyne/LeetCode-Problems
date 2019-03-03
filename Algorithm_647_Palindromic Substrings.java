// 思路：构造二维数组dp，dp[i][j]表示以s[i]开始、s[j]结尾的substring，其值为该substring是否为palindrome
// 二维数组的遍历思路类似于Algorithm_516
// start递减，当s[start]==s[end]时，若start == end - 1，两字符相邻，则直接构成palindrome，置dp[start][end] = true
// 否则，是否构成palindrome取决于s[start + 1] ~ s[end - 1]
// 每向dp[start][end]放一个true，就将sum++
// 最终sum就是所有palindromic substring的总数

class Solution {
    public int countSubstrings(String s) {
        int sum = s.length(); // every single char is a palindrome substring
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++)
            dp[i][i] = true;
        for (int start = s.length() - 1; start >= 0; start--) {
            for (int end = start + 1; end < s.length(); end++) {
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = start == end - 1 ? true : dp[start + 1][end - 1];
                    if(dp[start][end]) sum++;
                }
            }
        }
        return sum;
    }
}

// 2D array -> 1D array
class OptimizedSolution {
    public int countSubstrings(String s) {
        int sum = s.length(); // every single char is a palindrome substring
        boolean[] dp = new boolean[s.length()];
        for (int start = s.length() - 1; start >= 0; start--) {
            boolean[] tempDp = new boolean[s.length()];
            for (int end = start + 1; end < s.length(); end++) {
                if (s.charAt(start) == s.charAt(end)) {
                    tempDp[end] = end - start <= 2 ? true : dp[end - 1];
                    if(tempDp[end]) sum++;
                }
            }
            dp = tempDp;
        }
        return sum;
    }
}



// Extend Palindrome Solution
class ExtendPalindromeSolution {
    int count = 0;
        
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }
}