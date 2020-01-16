class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if not prices:
            return 0
        haveStock = -prices[0]
        notHaveStock = 0
        cooldown = 0
        for i in range(1, len(prices)):
            newHaveStock = max(haveStock, notHaveStock - prices[i])
            newCooldown = haveStock + prices[i]
            newNotHaveStock = max(notHaveStock, cooldown)
            haveStock, notHaveStock, cooldown = newHaveStock, newNotHaveStock, newCooldown
        return max(notHaveStock, cooldown)