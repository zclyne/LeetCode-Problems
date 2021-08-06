import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 本质上就是找图中的所有无论怎么走都不会走到环路上的点

// 方法1：定义两个set invalidNodes和result
// 然后遍历所有点，进行dfs。
// 如果当前节点cur已经在invalidNodes中，或者是存在于visited中，说明dfs到当前节点为止的路径上所有点都是invalid的
// 将它们加入invalidNodes，然后返回false
// 如果当前节点cur已经在result中，说明cur是一个safe的点，return true
// 然后从graph中取到cur的所有后继节点。如果cur没有后继节点，说明cur是safe的，将cur加入result，然后返回true
// 如果cur的后继节点不为空，则对cur的后继节点进行遍历，继续DFS。只要有一个DFS返回值是false，则说明cur会走到
// 不safe的点上，所以cur自身也不是safe的。把cur加入invalidNodes，然后返回false
// 如果成功遍历完了cur的所有后继节点，说明cur的所有后继节点都是safe的，则cur自身也是safe的。将cur加入result，返回true
// 由于在dfs过程中，每个被访问到的节点一定会被加入invalidNodes或result这两个set中的某一个，并且对于已经在这两个set中的点
// 直接可以在O(1)时间内得到结果
// 因此，整个算法的时间复杂度是O(n + m)的，n是总节点个数，m是边数

class Solution {

    private Set<Integer> invalidNodes;
    private Set<Integer> result;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        invalidNodes = new HashSet<>();
        result = new HashSet<>();

        for (int i = 0; i < graph.length; i++) {
            if (!invalidNodes.contains(i) && !result.contains(i)) { // i is not visited yet
                helper(graph, i, new HashSet<>());
            }
        }

        // result should be in ascending order
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (result.contains(i)) {
                list.add(i);
            }
        }

        return list;
    }

    // return true means cur is safe, false means cur is not safe
    private boolean helper(int[][] graph, int cur, Set<Integer> visited) {
        if (invalidNodes.contains(cur) || visited.contains(cur)) { // will get into a loop or loop already detected
            invalidNodes.addAll(visited);
            return false;
        }
        if (result.contains(cur)) {
            return true;
        }

        int[] neighbors = graph[cur];
        if (neighbors.length == 0) {
            result.add(cur);
            return true;
        }

        visited.add(cur);
        for (int node : neighbors) {
            if (!helper(graph, node, visited)) { // this neighbor is invalid, so cur is also invalid
                visited.remove(cur);
                invalidNodes.add(cur);
                return false;
            }
        }

        // all the neighbor nodes are safe
        visited.remove(cur);
        result.add(cur);
        return true;
    }
}



// 方法2：拓扑排序
// 根据题意，若一个节点没有出边，则该节点是安全的；若一个节点出边相连的点都是安全的，则该节点也是安全的
// 根据这一性质，我们可以将图中所有边反向，得到一个反图，然后在反图上运行拓扑排序
// 具体来说，首先得到反图rg及其入度数组inDeg，将所有入度为0的点加入队列
// 然后不断取出队首元素，将其出边相连的点的入度减一，若该点入度减一后为0，则将该点加入队列，如此循环直至队列为空
// 循环结束后，所有入度为0的节点均为安全的。我们遍历入度数组，并将入度为0的点加入答案列表
// https://leetcode-cn.com/problems/find-eventual-safe-states/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-isy6u/

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 构建反向图rg及其入度数组inDeg
        List<List<Integer>> rg = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            rg.add(new ArrayList<Integer>());
        }
        int[] inDeg = new int[n];
        for (int x = 0; x < n; ++x) {
            for (int y : graph[x]) {
                rg.get(y).add(x);
            }
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : rg.get(y)) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}