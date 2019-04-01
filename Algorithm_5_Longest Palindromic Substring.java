// 思路：数组dp[i][j]表示从i开始、以j结尾的substring是否为回文
// 对于长度为1、2、3的字符串，只需要判断s[i]与s[j]是否相等。若相等则是回文
// 否则，不仅要求s[i] == s[j]，还要求dp[i + 1][j - 1]是true，整个字符串才构成回文
// 注意数组dp的更新顺序为沿对角线更新，初始状态是dp[i][i] = true

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 1, maxStart = 0, maxEnd = 0;
        for (int length = 0; length < s.length(); length++) {
            for (int row = 0; row + length < s.length(); row++){
                if (length == 0) {
                    dp[row][row + length] = true;
                } else if (length == 1 || length == 2) {
                    dp[row][row + length] = s.charAt(row) == s.charAt(row + length);
                } else {
                    dp[row][row + length] = dp[row + 1][row + length - 1] & s.charAt(row) == s.charAt(row + length);
                }
                if (dp[row][row + length] && length + 1 > maxLen) {
                    maxLen = length + 1;
                    maxStart = row;
                    maxEnd = row + length;
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}

// solution with better performance
// 思路：对每一个字符，向前后两个方向扩展，判断左右两个字符是否相等，若相等，则构成回文，长度+2

// public class Solution {
//     private int lo, maxLen;
    
//     public String longestPalindrome(String s) {
//         int len = s.length();
//         if (len < 2)
//             return s;
        
//         for (int i = 0; i < len-1; i++) {
//              extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
//              extendPalindrome(s, i, i+1); //assume even length.
//         }
//         return s.substring(lo, lo + maxLen);
//     }
    
//     private void extendPalindrome(String s, int j, int k) {
//         while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
//             j--;
//             k++;
//         }
//         if (maxLen < k - j - 1) {
//             lo = j + 1;
//             maxLen = k - j - 1;
//         }
//     }}