import java.util.ArrayList;

// 思路：对于一个正方形格子，它可以被两条对角线分割成上下左右共4个三角形
// 因此设并查集的初始大小为4*n^2，根据每个格子内的字符情况，合并相应的三角形
// 如果当前格子中的字符为空，则4个三角形都能够合并
// 如果当前格子中的字符为'/'，则分别合并上、左以及右、下
// 如果当前格子中的字符为'\'，则分别合并左、下以及上、右
// 以上考虑的是单个格子内部的情况
// 对于格子之间的关系，又有以下规律：
// 一个格子内的左三角形和这个格子左侧相邻格子的右三角形必然连通
// 一个格子内的上三角形和这个格子上方相邻格子的下三角形必然连通
// 对于左、上、右、下三角形分别标以序号0,1,2,3，然后用并查集即可解决

class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        char[][] gridChars = new char[n][n];

        // convert from String[] to char[][]
        for (int i = 0; i < n; i++) {
            gridChars[i] = grid[i].toCharArray();
        }

        // each grid might be divided into 4 areas, so initialize the unionfind with 4 * n^2
        UnionFind uf = new UnionFind(4 * n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int idx = 4 * (i * n + j);
                if (gridChars[i][j] == '/') { // merge top and left, bottom and right
                    uf.merge(idx, idx + 1);
                    uf.merge(idx + 2, idx + 3);
                } else if (gridChars[i][j] == '\\') { // merge left and bottom, right and top
                    uf.merge(idx, idx + 3);
                    uf.merge(idx + 1, idx + 2);
                } else { // merge all 4 triangles
                    uf.merge(idx, idx + 1);
                    uf.merge(idx + 1, idx + 2);
                    uf.merge(idx + 2, idx + 3);
                }
                // merge with top adjacent grid
                if (i > 0) {
                    uf.merge(idx + 1, 4 * ((i - 1) * n + j) + 3);
                }
                // merge with left adjacent grid
                if (j > 0) {
                    uf.merge(idx, 4 * (i * n + j - 1) + 2);
                }
            }
        }
        return uf.getNumComponents();
    }
}

class UnionFind {
    private int numComponents;
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        numComponents = n;
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