import java.util.*;

// 方法1：DFS。把在同一行或同一列的stones放在同一个group里
// 最后删除的stone数量就等于stone总数减去最终的group数量

class Solution {
    private Set<int[]> visited;
    private int countGroups;

    public int removeStones(int[][] stones) {
        this.countGroups = 0;
        this.visited = new HashSet<>();
        for (int[] stone : stones) {
            // can form a new group
            if (!this.visited.contains(stone)) {
                this.countGroups++;
                dfs(stones, stone);
            }
        }
        return stones.length - this.countGroups;
    }

    private void dfs(int[][] stones, int[] cur) {
        if (this.visited.contains(cur)) {
            return;
        }
        this.visited.add(cur);
        for (int[] stone : stones) {
            // found a new stone in the same row or column
            if (stone[0] == cur[0] || stone[1] == cur[1]) {
                dfs(stones, stone);
            }
        }
    }
}

// 方法2：并查集。对于两个在同一行或同一列的stone，用并查集将他们merge起来
// 最后remove的stone数量就等于stone总数减去uf中的group数量

class Solution2 {
    class UnionFind {

        private int[] fa;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            this.count = n;
            this.fa = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]);
                return fa[x];
            }
            return fa[x];
        }

        public void merge(int i, int j) {
            int x = find(i), y = find(j);
            if (x == y) {
                return;
            }
            if (rank[x] <= rank[y]) { // merge x into y
                fa[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            } else { // merge y into x
                fa[y] = x;
            }
            this.count--;
        }

        public int getCount() {
            return this.count;
        }
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            int[] s1 = stones[i];
            for (int j = i + 1; j < n; j++) {
                int[] s2 = stones[j];
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    uf.merge(i, j);
                }
            }
        }
        return n - uf.getCount();
    }
}