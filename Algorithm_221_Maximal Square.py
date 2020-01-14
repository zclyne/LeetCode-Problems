class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        dp = [[0 for _ in range(len(matrix[0]))] for _ in range(len(matrix))]
        maxLen = 0
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == '1':
                    upper = 0 if i == 0 else dp[i - 1][j]
                    left = 0 if j == 0 else dp[i][j - 1]
                    upperLeft = 0 if i == 0 or j == 0 else dp[i - 1][j - 1]
                    dp[i][j] = 1 + min(left, upper, upperLeft)
                    maxLen = max(maxLen, dp[i][j])
        return maxLen ** 2