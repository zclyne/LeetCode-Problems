import java.util.LinkedList;
import java.util.Queue;

// 方法1：状态压缩+BFS
// 队列中放入一个三元组(node, mask, dist)。其中node表示当前的出发节点的编号
// mask用于记录节点是否已经被访问过。若第i个节点已经被访问过，则mask的第i位为1。即(mask >> i) & 1 == 1
// 由于n最大不超过12，所以一个32位的int足够表示所有节点的访问状态
// dist记录当前已经走过的总距离
// 为了去重，防止死循环，定义了一个二维数组seen。其第一维表示当前的出发节点node，第二维是mask
// 这样定义的原因在于，由(node, mask)可以唯一确定一个状态。如果有两个状态的node和mask都相同，而dist不同
// 则dist较大的那个没有意义，因为相当于多走了很多路，但是却没有访问到任何新的节点，并且还回到了node
// BFS过程中，如果当前状态已经在seen中为true，则可以直接continue，不做任何处理
// 否则，从graph获取当前节点的所有相邻节点，分别以每一个相邻节点为起点，更新mask和dist，并作为新的状态push到queue中
// 直到最后mask == (1 << n) - 1，表明所有节点都已经被访问过了，返回这个状态对应的dist
// 时间复杂度：O(n^2 * 2^n)

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }
}



// 方法2：预处理点对间最短路+状态压缩DP
// 对于任意一条经过所有节点的路径，它的某一个子序列（可以不连续）一定是0, 1, ⋯, n − 1的一个排列。我们称这个子序列上的节点为关键节点
// f[u][mask]表示从任一节点开始到节点u为止，并且经过的关键节点对应的二进制表示为mask时的最短路径长度
// 由于u是最后一个关键节点，那么在进行状态转移时，我们可以枚举上一个关键节点v
// 当mask中只包含一个1时，无法枚举出上一个关键节点v，说明我们位于开始的节点，还未经过任何路径，所以f[u][mask] = 0
// https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/solution/fang-wen-suo-you-jie-dian-de-zui-duan-lu-mqc2/

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(d[i], n + 1);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) {
                d[i][j] = 1;
            }
        }
        // 使用floyd算法计算出图上任意两点间的最短路径长度
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        int[][] f = new int[n][1 << n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE / 2);
        }
        for (int mask = 1; mask < (1 << n); ++mask) {
            // 如果 mask 只包含一个1，说明是起点
            if ((mask & (mask - 1)) == 0) {
                int u = Integer.bitCount((mask & (-mask)) - 1);
                f[u][mask] = 0;
            } else {
                for (int u = 0; u < n; ++u) {
                    if ((mask & (1 << u)) != 0) { // 点u已经被访问过
                        for (int v = 0; v < n; ++v) {
                            if ((mask & (1 << v)) != 0 && u != v) { // 枚举u的上一个关键节点v
                                f[u][mask] = Math.min(f[u][mask], f[v][mask ^ (1 << u)] + d[v][u]);
                            }
                        }
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int u = 0; u < n; ++u) {
            ans = Math.min(ans, f[u][(1 << n) - 1]);
        }
        return ans;
    }
}