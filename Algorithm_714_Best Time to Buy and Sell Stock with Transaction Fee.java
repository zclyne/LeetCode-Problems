// 思路：动态规划
// 可以优化到O(1)空间复杂度

class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] haveStock = new int[prices.length];
        int[] notHaveStock = new int[prices.length];
        haveStock[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            haveStock[i] = Math.max(haveStock[i - 1], notHaveStock[i - 1] - prices[i]);
            notHaveStock[i] = Math.max(notHaveStock[i - 1], haveStock[i - 1] + prices[i] - fee);
        }
        return notHaveStock[prices.length - 1];
    }
}