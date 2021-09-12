import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private int[] result;
    private boolean[][] cache;
    private boolean[] visited;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        result = new int[n];
        cache = new boolean[n][100001];
        visited = new boolean[n];

        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = parents[i];
            List<Integer> subNodes = tree.getOrDefault(parent, new ArrayList<>());
            subNodes.add(i);
            tree.put(parent, subNodes);
        }

        dfs(tree, nums, 0);

        return result;
    }

    private void dfs(Map<Integer, List<Integer>> tree, int[] nums, int cur) {
        if (visited[cur]) {
            return;
        }

        List<Integer> subNodes = tree.getOrDefault(cur, new ArrayList<>());
        int curValue = nums[cur];

        for (int node : subNodes) {
            dfs(tree, nums, node);
        }

        for (int i = 1; i <= 100000; i++) {
            for (int node : subNodes) {
                if (cache[node][i]) {
                    cache[cur][i] = true;
                    break;
                }
            }
        }
        cache[cur][curValue] = true;
        visited[cur] = true;

        for (int i = 1; i <= 100000; i++) {
            if (!cache[cur][i]) {
                result[cur] = i;
                return;
            }
        }
        
        result[cur] = 100001;
    }
}