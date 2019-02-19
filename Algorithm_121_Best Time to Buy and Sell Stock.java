// 思路：直接DP。用curMin记录下从第0天开始到第i - 1天所遇到的最低的价格，作为购入价格
// 若在第i天卖出，则盈利为prices[i] - curMin
// 注意题目表述：最多只能进行一次买入卖出

class Solution {
    public int maxProfit(int[] prices) {
        int curMin = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return res;
    }
}

// Kadane's Algorithm，找到一个连续子数组，使得盈利最大
// 当maxCur < 0时，说明亏本，把maxCur置0，重新开始计算
// 可以防止数组中出现负数

class Solution2 {
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
}