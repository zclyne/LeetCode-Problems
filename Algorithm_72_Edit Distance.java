// 思路：dp[i][j]存放把word1[0, i - 1]转换成word2[0, j - 1]所需的最少步骤数
// 初始情况是dp[i][0] = i以及dp[0][j] = j
// 前者表示把word1[0, i - 1]变为空字符串需要i次删除。后者表示把空字符串变为word2[0, j - 1]需要j次插入
// 对于一般情况d[[i][j]，当word1[i - 1] == word2[j - 1]时，不需要任何操作，因此dp[i][j] = dp[i - 1][j - 1]
// 当word1[i - 1] != word2[j - 1]时，有三种情况：
// 1. 已经把word1[0, i - 2]转变成了word2[0, j - 2]，因此要把word1[i - 1]替换成word2[j - 1]，则dp[i][j] = dp[i - 1][j - 1] + 1
// 2. 已经把word1[0, i - 2]转变成了word2[0, j - 1]，因此要删除word1[i - 1]，则dp[i][j] = dp[i - 1][j] + 1
// 3. 已经把word1[0, i - 1]转变成了word2[0, j - 2]，因此要插入word2[j - 1]，则dp[i][j] = dp[i][j - 1] + 1
// 取以上三种情况中的最小值作为dp[i][j]

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // no action
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}