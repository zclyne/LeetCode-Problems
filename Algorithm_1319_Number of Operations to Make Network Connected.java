import java.util.ArrayList;

// 思路：节点数量为n的图，必须有n-1条边才能把整个图变为连通图
// 因此如果connections.length < n - 1，则必然不存在结果，返回-1
// 如果connections.length >= n - 1，则要根据图的连通分量的个数来判断
// 假设有k个连通分量，那么就需要额外的k - 1条边来把这些分量连接起来
// 这额外的k - 1条边是从具有多余边的连通分量内部移动得到的
// 因此，这道题的本质是求图的连通分量个数
// 可以使用DFS和并查集两种方法来解决

// DFS解法

class Solution {
    private List<List<Integer>> edges;
    private boolean[] visited;
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        visited = new boolean[n];
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        // store connections in edges
        for (int[] connection : connections) {
            edges.get(connection[0]).add(connection[1]);
            edges.get(connection[1]).add(connection[0]);
        }

        // traverse every vertex in the graph, and get the number of connected components
        int numComponents = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                DFS(i);
                numComponents++;
            }
        }

        return numComponents - 1;
    }

    private void DFS(int i) {
        visited[i] = true;
        List<Integer> edgesFromI = edges.get(i);
        for (int j : edgesFromI) {
            if (!visited[j]) {
                DFS(j);
            }
        }
    }
}

// 并查集解法

class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.merge(connection[0], connection[1]);
        }
        return unionFind.getNumComponents() - 1;
    }
}

class UnionFind {
    private int numComponents;
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        numComponents = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void merge(int i, int j) {
        int x = find(i), y = find(j);
        if (x == y) {
            return;
        }
        numComponents--;
        if (rank[x] <= rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        if (rank[x] == rank[y]) {
            rank[y]++;
        }
    }

    public int getNumComponents() {
        return this.numComponents;
    }
}