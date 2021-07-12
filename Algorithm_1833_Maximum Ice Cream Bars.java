import java.util.Arrays;
// 解法：排序+贪心法
// 假设购买最便宜的雪糕，在总价格不超过coins的情况下最多可以购买k支雪糕。如果将k支最便宜的雪糕中的任意一支雪糕替换成另一支雪糕
// 则替换后的雪糕的价格大于或等于替换前的雪糕的价格，因此替换后的总价格大于或等于替换前的总价格，允许购买的雪糕数量不可能超过k。因此可以买到的雪糕的最大数量为k。
// 贪心法能获得正确答案的证明：https://leetcode-cn.com/problems/maximum-ice-cream-bars/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-yrhjx/

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int result = 0;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] <= coins) {
                coins -= costs[i];
                result++;
            }
        }
        return result;
    }
}