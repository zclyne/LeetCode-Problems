import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;

// 解法1：BFS
// 先用map存储各个人及其他能传递的目标数组之间的映射，变量step记录当前的层数

class Solution {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            int source = relation[i][0], target = relation[i][1];
            ArrayList<Integer> targets = map.getOrDefault(source, new ArrayList<>());
            targets.add(target);
            map.put(source, targets);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int step = 1, result = 0;
        while (!queue.isEmpty() && step <= k) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int cur = queue.poll();
                ArrayList<Integer> targets = map.getOrDefault(cur, new ArrayList<>());
                for (int target : targets) {
                    queue.offer(target);
                }
            }
            step++;
        }

        while (!queue.isEmpty()) {
            if (queue.poll() == n - 1) {
                result++;
            }
        }

        return result;
    }
}

// 解法2：动态规划
// dp[i][j]为经过i轮传递到编号j的玩家的方案数
// 当i = 0时，一定位于编号0的玩家，不会传递到其他玩家，因此动态规划的边界情况如下
// 1. dp[0][j] = 1, j = 0
// 2. dp[0][j] = 0, j != 0

class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                dp[i + 1][dst] += dp[i][src];
            }
        }
        return dp[k][n - 1];
    }
}