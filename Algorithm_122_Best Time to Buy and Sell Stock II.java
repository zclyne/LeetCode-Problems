// 思路：直接动态规划，haveStock存储当前持有stock时的最大profit，notHaveStock存储当前不持有stock时的最大profit

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int haveStock = -prices[0];
        int notHaveStock = 0;
        for (int i = 1; i < prices.length; i++) {
            int curHaveStock = Math.max(haveStock, notHaveStock - prices[i]);
            int curNotHaveStock = Math.max(notHaveStock, haveStock + prices[i]);
            haveStock = curHaveStock;
            notHaveStock = curNotHaveStock;
        }
        return notHaveStock;
    }
}