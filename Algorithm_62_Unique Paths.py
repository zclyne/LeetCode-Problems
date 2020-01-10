class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = [[0 for _ in range(m)] for _ in range(n)]
        dp[0][0] = 1
        for i in range(n):
            for j in range(m):
                if i == 0 and j == 0:
                    continue
                leftNum = 0 if i == 0 else dp[i - 1][j]
                topNum = 0 if j == 0 else dp[i][j - 1]
                dp[i][j] = leftNum + topNum
        return dp[n - 1][m - 1]