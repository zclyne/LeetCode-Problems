import java.util.*;

// 方法：简单DFS

class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result = new ArrayList<>();
        int n = graph.length;
        dfs(graph, 0, n - 1, new ArrayList<>());
        return result;
    }

    private void dfs(int[][] graph, int cur, int target, List<Integer> path) {
        if (cur == target) {
            List<Integer> curPath = new ArrayList<>(path);
            curPath.add(cur);
            this.result.add(curPath);
            return;
        }
        path.add(cur);
        int[] nextNodes = graph[cur];
        for (int nextNode : nextNodes) {
            dfs(graph, nextNode, target, path);
        }
        path.remove(path.size() - 1);
    }
}