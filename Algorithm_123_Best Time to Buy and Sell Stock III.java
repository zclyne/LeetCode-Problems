// 思路：变量firstTimeHasStock，firstTimeTransactionComplete分别记录在第一次交易时，持有股票时利润的最大值和卖出股票后利润的最大值
// secondTimeHasStock, secondTimeTransactionComplete同理
// 无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零
// 由于当天买入股票再卖出，所得的profit是0，不影响结果，所以secondTimeTransactionComplete就是最终的结果，包含了只交易1次和交易了2次的情况
// 同理，hasStock可以在transactionComplete变量之前更新，不会影响结果
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-wrnt/

class Solution {
    public int maxProfit(int[] prices) {
        int firstTimeHasStock = Integer.MIN_VALUE;
        int firstTimeTransactionComplete = 0;
        int secondTimeHasStock = Integer.MIN_VALUE;
        int secondTimeTransactionComplete = 0;
        for (int i = 0; i < prices.length; i++) {
            // buy stock for the first time
            firstTimeHasStock = Math.max(firstTimeHasStock, -prices[i]);
            firstTimeTransactionComplete = Math.max(firstTimeTransactionComplete, firstTimeHasStock + prices[i]);
            // buy stock for the second time
            secondTimeHasStock = Math.max(secondTimeHasStock, firstTimeTransactionComplete - prices[i]);
            secondTimeTransactionComplete = Math.max(secondTimeTransactionComplete, secondTimeHasStock + prices[i]);
        }
        return secondTimeTransactionComplete;
    }
}