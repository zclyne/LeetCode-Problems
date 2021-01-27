// 思路：公共边的优先级高于alice和bob各自的独占边
// 因为假设某条公共边能够连接两个连通分量，如果我们不保留这条公共边，那么alice和bob就分别需要一条
// 连接这两个连通分量的独占边来保证他们能够往返于这两个连通分量之间
// 因此，对于一条公共边的两个节点，如果这两个节点在同一个连通分量中，则不需要添加这条边
// 如果两个节点分属于两个不同的连通分量，则添加这条公共边，并且在并查集中合并这两个节点
// 处理完公共边后再处理独占边

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;

        // 节点编号改为从 0 开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        // 公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.unite(edge[1], edge[2])) { // 合并失败，说明两节点原本就属于同一个连通分量
                    ++ans;
                } else {
                    ufb.unite(edge[1], edge[2]);
                }
            }
        }

        // 独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) { // Alice 独占边
                if (!ufa.unite(edge[1], edge[2])) { // 合并失败，说明两节点原本就属于同一个连通分量
                    ++ans;
                }
            } else if (edge[0] == 2) { // Bob 独占边
                if (!ufb.unite(edge[1], edge[2])) { // 合并失败，说明两节点原本就属于同一个连通分量
                    ++ans;
                }
            }
        }

        if (ufa.setCount != 1 || ufb.setCount != 1) {
            return -1;
        }
        return ans;
    }
}

class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    
    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }
    
    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }
    
    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}