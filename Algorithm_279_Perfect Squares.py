# Python的DP算法会出现TLE
# class Solution:
#     def numSquares(self, n: int) -> int:
#         dp = [0 for _ in range(n + 1)]
#         i = 1
#         while i ** 2 <= n:
#             dp[i ** 2] = 1
#             i += 1
#         if dp[n] != 0:
#             return dp[n]
#         for i in range(2, n + 1):
#             if dp[i] == 0:
#                 curMin = sys.maxsize
#                 j = 1
#                 while j ** 2 <= i:
#                     curMin = min(curMin, 1 + dp[i - j ** 2])
#                     j += 1
#                 dp[i] = curMin
#         return dp[n]



# BFS算法
class Solution:
    def numSquares(self, n: int) -> int:
        perfectSquares = []
        visited = [False for _ in range(n + 1)]
        i = 1
        while i ** 2 <= n:
            perfectSquares.append(i ** 2)
            visited[i ** 2] = True
            i += 1
        if visited[n]:
            return 1
        queue = []
        for squareNum in perfectSquares:
            queue.append(squareNum)
        count = 1
        while queue:
            count += 1
            size = len(queue)
            for _ in range(size):
                curNum = queue.pop(0)
                for squareNum in perfectSquares:
                    if curNum + squareNum == n:
                        return count
                    elif curNum + squareNum < n and not visited[curNum + squareNum]:
                        visited[curNum + squareNum] = True
                        queue.append(curNum + squareNum)
                    elif curNum + squareNum > n:
                        break