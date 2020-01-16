class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0
        dp = [sys.maxsize for _ in range(amount + 1)]
        for coin in coins:
            if coin <= amount:
                dp[coin] = 1
        for i in range(amount + 1):
            if dp[i] == sys.maxsize:
                for coin in coins:
                    if coin < i and dp[i - coin] != sys.maxsize:
                        dp[i] = min(dp[i], dp[i - coin] + 1)
        return -1 if dp[-1] == sys.maxsize else dp[-1]