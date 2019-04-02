// 思路：画有限状态机，共有3种状态：haveStock、sell、noTransaction
// 1. haveStock[i]有2种情况：noTransaction[i - 1] - prices[i]或haveStock[i - 1]，分别对应于在第i天买入stock和原本就有stock
// 要使在第i天买入stock后拥有的钱最多，则要取二者的max
// 2. sell[i]只有可能haveStock[i - 1] + prices[i]
// 3. noTransaction[i]有2种情况：noTransaction[i - 1]或sell[i - 1]，分别对应于无stock且不买入和在第i - 1天卖出
// 要是在第i天无stock且不做任何操作时拥有的钱最多，取二者的max
// 最后结果为max(noTransaction, sell)，因为一定不会在最后一天买入后获得更多的钱


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int haveStock = -prices[0], sell = 0, noTransaction = 0;
        for (int i = 1; i < prices.length; i++) {
            int newHaveStock = Math.max(haveStock, noTransaction - prices[i]);
            int newSell = haveStock + prices[i];
            int newNoTransaction = Math.max(noTransaction, sell);
            haveStock = newHaveStock;
            sell = newSell;
            noTransaction = newNoTransaction;
        }
        return Math.max(noTransaction, sell);
    }
}