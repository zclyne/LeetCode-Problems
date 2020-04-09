import java.util.Arrays;

// 思路：二维数组isPalindrome用于存储子字符串是否为回文，isPalindrome[i][j] == true表示s.substring(i, j + 1)为回文
// cut[i] != Integer.MAX_VALUE时，表示s.substring(i, s.length())可以被分为若干个回文字符串的拼接
// 并且cut[i]存储s.substring(i, s.length())的所有拼接方式中，cut最少的次数
// 注意i从大到小遍历。当寻找到一对(i, j)为回文时，首先判断j是否为最后一个字符的下标，若是，则cut[i] = 0
// 若不是，且cut[j + 1] != Integer.MAX_VALUE，则表明s.substring(j + 1, s.length())可以由若干回文字符串组成
// cut[i]相当于在cut[j + 1]的基础上再加1，但是要注意应先判断cut[i]和cut[j + 1]的关系
// 因此判断条件可以简化成cut[j + 1] + 1 < cut[i]

class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int[] cut = new int[s.length()];
        Arrays.fill(cut, Integer.MAX_VALUE);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                    if (j == s.length() - 1) {
                        cut[i] = 0;
                    } else if (j < s.length() - 1 && cut[j + 1] + 1 < cut[i]) {
                        cut[i] = 1 + cut[j + 1];
                    }
                }
            }
        }
        return cut[0];
    }
}

// 优化后的算法，只需要O(n)空间复杂度
// 思路：cut[i]存储s.substring(0, i + 1)的最少cut数

class OptimizedSolution {
    public int minCut(String s) {
        int n = s.length();
        int[] cut = new int[n + 1];  // number of cuts for the first k characters
        for (int i = 0; i <= n; i++) cut[i] = i-1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; i-j >= 0 && i+j < n && s.charAt(i - j)==s.charAt(i + j); j++) // odd length palindrome
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j]);

            for (int j = 1; i-j+1 >= 0 && i+j < n && s.charAt(i - j + 1) == s.charAt(i + j); j++) // even length palindrome
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j+1]);
        }
        return cut[n];
    }
}