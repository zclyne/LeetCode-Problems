// 思路：构造二维数组dp，第一维表示目的城市，第二维表示stop数
// 初始化时，将所有dp[i][j]置为Integer.MAX_VALUE，表示无法从src以j次stop到达城市i
// 若flight中有起始位置为src的，则把dp[flight[1]][0]置为flight[2]，表示可以直接从src到达城市flight[1]，票价为flight[2]
// 随后，按照stop数增加的顺序，每个stop数遍历一次flights，若存在dp[flight[0]][k - 1]不等于Integer.MAX_VALUE
// 表明存在一条stop数为k - 1的、由src出发到达城市flight[0]的路径
// 由由于flight[0]与flight[1]之间存在航班，票价为flight[2]，则dp[flight[1]][k]要取其原本的值和dp[flight[0]][k - 1] + flight[2]的较小值
// 这个较小值就是从src出发、到达城市flight[1]且stop数为k的路径总票价
// 嵌套循环完成后，对目的地为dst的这一行做遍历，找到其中最小的值，即为最优路径
// 若最小值等于Integer.MAX_VALUE，说明路径不存在，返回-1

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n][K + 1]; // destination, numOfStops
        for (int i = 0; i < n; i++) { // initialize
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] flight : flights) {
            if (src == flight[0]) { // the flight starts from src, no stop
                dp[flight[1]][0] = flight[2];
            }
        }
        for (int k = 1; k <= K; k++) {
            for (int[] flight : flights) {
                if (dp[flight[0]][k - 1] != Integer.MAX_VALUE) { // there exists a path from src to flight[0]
                    dp[flight[1]][k] = Math.min(dp[flight[1]][k], dp[flight[0]][k - 1] + flight[2]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int k = 0; k < K + 1; k++) {
            res = Math.min(res, dp[dst][k]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}