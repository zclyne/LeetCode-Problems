// 方法1：暴力法，TLE
// 在区间[1, n]中任选一个数字i作为当前的guess。如果i猜错了，则要递归地在区间[1, i - 1]和[i + 1, n]继续计算
// O(n!)时间复杂度

class Solution1 {
    public int calculate(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate(i + 1, high), calculate(low, i - 1));
            minres = Math.min(res, minres);
        }

        return minres;
    }
    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }
}

// 方法2：DP
// 从暴力法可以看出，在一个区间[i, j]内进行猜测的最小的开销可以由两个更小的区间[i, k - 1], [k + 1, j]的开销的较大值再加上k得到
// 这启发我们，可以使用dp来解决这个问题
// dp[i][j]表示在区间[i, j]中找到任意一个guess的最小开销
// 因此，可以对[i, j]进行遍历，记当前guess为k，则k + Math.max(dp[i][k - 1], dp[k + 1][j])就是以k为当前guess时，在[i, j]区间内猜测的最小开销
// 对于所有k，取使得k + Math.max(dp[i][k - 1], dp[k + 1][j])最小的，作为dp[i][j]的值
// 注意k等于i或j这两种情况要特殊处理
// dp的初始状态是dp[i][i] = 0，对于任何1 <= i <= n
// 因为区间中只有一个数，一定会猜对

class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) { // len is the length of the currently selected interval
            for (int i = 1; i + len - 1 <= n; i++) { // i is the starting index of the currently selected interval
                int j = i + len - 1; // j is the ending index of the currently selected interval
                // traverse through every number within [i, j]
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int left = k == i ? 0 : dp[i][k - 1];
                    int right = k == j ? 0 : dp[k + 1][j];
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(left, right));
                }
            }
        }

        return dp[1][n];
    }
}