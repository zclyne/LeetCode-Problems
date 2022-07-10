// 用adjacency matrix表示graph
// 用数组numRoads记录每个城市所连接的road的数量
// 然后遍历每一对城市pair，扣除重复计算
// O(n^2)

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] hasRoad = new boolean[n][n];
        for (int[] road : roads) {
            int city1 = road[0], city2 = road[1];
            hasRoad[city1][city2] = true;
            hasRoad[city2][city1] = true;
        }

        // calculate the number of directly connected roads of each city
        int[] numRoads = new int[n];
        for (int i = 0; i < n; i++) {
            numRoads[i] = getNumberOfConnectedRoads(hasRoad[i]);
        }

        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            // get the network rank of city pair (i, j)
            for (int j = i + 1; j < n; j++) {
                int networkRank = numRoads[i] + numRoads[j];
                if (hasRoad[i][j]) { // road connecting both city only counts once
                    networkRank--;
                }
                result = Math.max(result, networkRank);
            }
        }

        return result;
    }

    private int getNumberOfConnectedRoads(boolean[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            count += row[i] ? 1 : 0;
        }
        return count;
    }
}