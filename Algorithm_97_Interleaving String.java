// DP Solution
// 思路：dp[i][j]表示用s1中的前i个字符和s2中的前j个字符，能否interweave构成s3中的前i + j个字符
// base case是dp[0][0] = true，表示都取0个字符可以构成空字符串
// 递推公式是
// if (s1[i - 1] == s3[i + j - 1]) then dp[i][j] |= dp[i - 1][j]
// if (s2[j - 1] == s3[i + j - 1]) then dp[i][j] |= dp[i][j - 1]
// 注意这两种情况都要考虑，不是if ... else if的关系
// 最后dp[s1.length()][s2.length()]中存储的就是答案

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = s2.charAt(j - 1) == s3.charAt(j - 1) & dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = s1.charAt(i - 1) == s3.charAt(i - 1) & dp[i - 1][j];
                } else {
                    dp[i][j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) & dp[i - 1][j] | s2.charAt(j - 1) == s3.charAt(i + j - 1) & dp[i][j - 1];
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

// BFS solution
// 思路：https://leetcode.com/problems/interleaving-string/discuss/31948/8ms-C%2B%2B-solution-using-BFS-with-explanation
// 把s1和s2展开成二维的board的形式，则题目可以看作寻找一条从左上角到右下角的路径
// board的两个相邻cell之间由一个字符连结。例如，若s1 = "aab", s2 = "abc"，则board形如：
// o--a--o--b--o--c--o
// |     |     |     |
// a     a     a     a
// |     |     |     |
// o--a--o--b--o--c--o
// |     |     |     |
// a     a     a     a
// |     |     |     |
// o--a--o--b--o--c--o
// |     |     |     |
// b     b     b     b
// |     |     |     |
// o--a--o--b--o--c--o
// 每个o代表一个cell
// 在求解时，每次只能向右或向下走1格，最差情况下的时间复杂度为O(mn)，整体来看比DP算法更快
// 同样思路也可以用DFS来解

class BFSSolution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (visited[p[0]][p[1]]) {
                continue;
            }
            if (p[0] == s1.length() && p[1] == s2.length()) {
                return true;
            }
            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1])) {
                queue.offer(new int[]{p[0] + 1, p[1]});
            }
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1])) {
                queue.offer(new int[]{p[0], p[1] + 1});
            }
            visited[p[0]][p[1]] = true;
        }
        return false;
    }
}

class DFSSolution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return DFS(s1, s2, s3, 0, 0, new boolean[s1.length() + 1][s2.length() + 1]);
    }
    private boolean DFS(String s1, String s2, String s3, int i, int j, boolean[][] invalid) {
        if (invalid[i][j]) {
            return false;
        }
        if (i == s1.length() && j == s2.length()) {
            return true;
        }
        // write result into this pattern greatly improves efficiency because if the part before || is true,
        // the second part would not be executed
        boolean result = i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && DFS(s1, s2, s3, i + 1, j, invalid)
                      || j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && DFS(s1, s2, s3, i, j + 1, invalid);
        if (!result) {
            invalid[i][j] = true;
        }
        return result;
    }
}