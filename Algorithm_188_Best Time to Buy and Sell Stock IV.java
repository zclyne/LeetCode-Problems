// 思路：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-iv-by-8xtkp/
// buy[i][j]表示在第i天，进行了j次交易后并且持有股票时的最大收益
// sell[i][j]表示在第i天，进行了j次交易后并且不持有股票时的最大收益
// 在第i天，buy[i][j]存在两种情况：
// 1. 在第i天买入股票，则第i - 1天不能持有股票，对应于buy[i][j] = sell[i - 1][j] - prices[i]
// 2. 在第i天不买入股票，对应于buy[i][j] = buy[i - 1][j]
// 同理，sell[i][j]存在两种情况：
// 1. 在第i天卖出股票，则第i - 1天应持有股票，并且多完成了一笔交易，对应于sell[i][j] = buy[i - 1][j - 1] + prices[i]
// 2. 在第i天不卖出股票，对应于sell[i][j] = sell[i - 1][j]
// 注意最终的答案不一定对应于正好完成k笔交易的情况，所以应遍历sell[n - 1]，取其中的最大值作为结果
// 本题需要格外注意边界情况，对于buy[0]来说，只有buy[0][0]是合法的，其初始值为-prices[0]
// 所以对于buy[0][1 ... k]，都可以用一个很小的值来表示不合法的状态，这里设为Integer.MIN_VALUE / 2
// sell[0][1 ... k]同理
// 由于n天最多进行n / 2次交易，所以在一开始可以对k和n / 2二者取较小值作为k的新值

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);   
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }
}