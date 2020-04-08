// 思路：动态规划
// dp[i][j]存储从三角形的最后一行向上走到第i行第j个元素时最小路径的和
// 这里采用自底向上的方法，比较简便
// dp[i][j]从二维数组优化到了一维数组
// 在循环执行完毕后，由于第1层只有1个元素，因此dp[0]就是答案

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 0; i < triangle.size(); i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}