// my solution思路：dp[i][j]表示当前已有i个A，而下一次paste时paste的A的个数为j，存储的值为达到这一状态所需的操作数
// 易知，当i、j确定时，操作数必然唯一
// 使用DFS的方法，若dp[i][j]为0，表示还未处理过该状态，分别对当前状态选择copy或paste，递归调用DFS
// 注意copy之前要比较pasteNum和curNum是否相等，若相等，表明上一层DFS时已经执行过了copy操作，不应该再次copy
// 最后，对dp[n][0]~dp[n][n]遍历，找到其中最小的非0值，即为最少所需的操作数

// better solution思路：将n个A划分为d个部分，每个部分的长度为n / d，共需d个操作（1个copy，d - 1个paste）
// 本质上是一个greedy的算法

// my solution
class Solution {
    private int[][] dp;
    public int minSteps(int n) {
        if (n == 1) return 0;
        dp = new int[n + 1][n + 1];
        DFS(1, n, 1, 1);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++)
            if (dp[n][i] != 0)
                res = Integer.min(res, dp[n][i]);
        return res;
    }
    public void DFS(int curNum, int n, int pasteNum, int numOfOperations) {
        if (curNum > n || dp[curNum][pasteNum] != 0) // situation is invalid or has already been visited
            return;
        else if (dp[curNum][pasteNum] == 0) { // not handled yet
            dp[curNum][pasteNum] = numOfOperations;
            // choose to copy
            if (pasteNum != curNum)
                DFS(curNum, n, curNum, numOfOperations + 1);
            // choose to paste
            DFS(curNum + pasteNum, n, pasteNum, numOfOperations + 1);
        }
    }
}

// better solution, no DP
class Solution {
    public int minSteps(int n) {
        int s = 0;
        for (int d = 2; d <= n; d++) {
            while (n % d == 0) {
                s += d;
                n /= d;
            }
        }
        return s;
    }
}