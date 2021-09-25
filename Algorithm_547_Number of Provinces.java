// 方法：并查集

class Solution {

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

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.merge(i, j);
                }
            }
        }
        return uf.getCount();
    }
}