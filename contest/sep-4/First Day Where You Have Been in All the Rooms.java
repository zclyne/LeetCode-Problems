// 方法：动态规划
// dp[i]表示第一次到达房间i时，所需要的天数
// 初始状态时第0天在房间0，所以dp[0] = 0
// 从第i天开始。第一次到达房间i时，一定是由于昨天在房间i - 1，并且访问房间i - 1的总次数是偶数次
// 此时的天数就等于一下几个部分相加：
// 1. 第一次到达房间i - 1所花费的天数，即dp[i - 1]。此时总次数为奇数
// 2. 由于访问房间i - 1的总次数为奇数，所以需要回退到房间nextVisit[i - 1]，回退操作也需要1天
// 3. 从回退回到的房间nextVisit[i - 1]回到房间i - 1所需要的天数，即dp[i - 1] - dp[nextVisit[i - 1]]
// 4. 此时第二次访问房间i - 1，总次数为偶数次，因此可以进入房间i，需要1天的时间
// 因此可以得到这样的递推关系式：dp[i] = dp[i - 1] + 1 + (dp[i - 1] - dp[next]) + 1
// 由于数字可能非常大，所以这里dp数组类型需要使用long，而非int
// 另外，由于取模操作的存在，上述关系式右边的结果可能是负数，需要再加上一个MOD后，再对MOD进行取模，从而把值调整成正数
// 最后，dp[n - 1]就是最终的答案

class Solution {
    private final int MOD = 1000000007;

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] dp = new long[n];
        for (int i = 1; i < n; i++) {
            int next = nextVisit[i - 1];
            dp[i] = (dp[i - 1] + 1 + (dp[i - 1] - dp[next]) + 1 + MOD) % MOD;
        }
        return (int) dp[n - 1];
    }
}