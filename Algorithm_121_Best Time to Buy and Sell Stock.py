class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if len(prices) <= 1:
            return 0
        curMin, res = prices[0], 0
        for i in range(1, len(prices)):
            res = max(res, prices[i] - curMin)
            curMin = min(curMin, prices[i])
        return res