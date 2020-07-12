// 思路：变量firstTimeHasStock，firstTimeTransactionComplete分别记录在第一次交易时，持有股票时利润的最大值和卖出股票后利润的最大值
// secondTimeHasStock, secondTimeTransactionComplete同理
// 遍历prices，首先更新两个second变量，这是因为second变量依赖于first变量，如果先更新first，逻辑上就变成了当天完成了第一笔交易之后
// 马上开始第二笔交易，不符合题目要求
// 同理，当天的交易依赖于前一天的结果，所以transactionComplete需要在hasStock之前更新，否则就变成了当天买入当天卖出

class Solution {
    public int maxProfit(int[] prices) {
        int firstTimeHasStock = Integer.MIN_VALUE;
        int firstTimeTransactionComplete = 0;
        int secondTimeHasStock = Integer.MIN_VALUE;
        int secondTimeTransactionComplete = 0;
        for (int i = 0; i < prices.length; i++) {
            // buy stock for the second time
            secondTimeTransactionComplete = Math.max(secondTimeTransactionComplete, secondTimeHasStock + prices[i]);
            secondTimeHasStock = Math.max(secondTimeHasStock, firstTimeTransactionComplete - prices[i]);
            // buy stock for the first time
            firstTimeTransactionComplete = Math.max(firstTimeTransactionComplete, firstTimeHasStock + prices[i]);
            firstTimeHasStock = Math.max(firstTimeHasStock, -prices[i]);
        }
        return Math.max(firstTimeTransactionComplete, secondTimeTransactionComplete);
    }
}