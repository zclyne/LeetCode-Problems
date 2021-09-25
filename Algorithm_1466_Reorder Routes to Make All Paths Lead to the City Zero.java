import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

// 方法：以0为根节点进行dfs
// 首先构建图inGraph和outGraph，记录进入每个节点的边和从每个节点出去的边
// 如果当前边是进入当前节点cur的，则继续dfs，并将这条边从next的outGraph中移除，防止重复计算
// 如果当前边是从当前节点cur触发的，则result++后继续dfs，表示需要把这条边反向，然后把这条边从next的inGraph中移除，防止重复计算

class Solution {

    private List<Set<Integer>> inGraph;
    private List<Set<Integer>> outGraph;
    private int result = 0;

    public int minReorder(int n, int[][] connections) {
        inGraph = new ArrayList<>();
        outGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inGraph.add(new HashSet<>());
            outGraph.add(new HashSet<>());
        }
        for (int[] connection : connections) {
            int from = connection[0], to = connection[1];
            inGraph.get(to).add(from);
            outGraph.get(from).add(to);
        }

        dfs(0);
        return result;
    }

    private void dfs(int cur) {
        for (int next : inGraph.get(cur)) {
            outGraph.get(next).remove(cur);
            dfs(next);
        }
        for (int next : outGraph.get(cur)) {
            inGraph.get(next).remove(cur);
            result++; // the edge from cur to next should be reversed
            dfs(next);
        }
    }
}