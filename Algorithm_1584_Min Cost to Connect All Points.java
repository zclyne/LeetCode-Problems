// 思路：https://leetcode-cn.com/problems/min-cost-to-connect-all-points/solution/prim-and-kruskal-by-yexiso-c500/
// 解法1：prim算法，以顶点为基础

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, result = 0;
        int[][] dists = new int[n][n];
        boolean[] addedToVnew = new boolean[n];
        int[] minDists = new int[n]; // records the min distance from each vertex in V to any vertex in Vnew

        // construct distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                dists[i][j] = dist;
                dists[j][i] = dist;
            }
        }

        // add vertex at index 0 to Vnew
        addedToVnew[0] = true;
        for (int i = 1; i < n; i++) {
            minDists[i] = dists[0][i];
        }

        // add verticies in V to Vnew one by one
        for (int i = 1; i < n; i++) {
            // select the vertex with the minimum dist
            int minDist = Integer.MAX_VALUE, minIndex = 0;
            for (int j = 0; j < n; j++) {
                if (!addedToVnew[j] && minDists[j] < minDist) {
                    minDist = minDists[j];
                    minIndex = j;
                }
            }
            // add vertex at minIndex to Vnew
            addedToVnew[minIndex] = true;
            result += minDist;

            // update minDists for each vertex
            for (int j = 0; j < n; j++) {
                if (!addedToVnew[j] && dists[j][minIndex] < minDists[j]) {
                    minDists[j] = dists[j][minIndex];
                }
            }
        }

        return result;
    }
}


// 解法2：Kruskal算法（并查集），以边为基础

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UnionFind dsu = new UnionFind(n);
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        // sort all edges by length
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge edge1, Edge edge2) {
                return edge1.len - edge2.len;
            }
        });

        int result = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.union(x, y)) { // x and y belongs to different component
                result += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return result;
    }

    public int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank; // records the depth of each root in order to speed up operations

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int i, int j) {
        int x = find(i), y = find(j);
        if (x == y) {
            return false;
        }
        if (rank[x] <= rank[y]) { // make y the root of x
            parent[x] = y;
        } else { // make x the root of y
            parent[y] = x;
        }
        if (rank[x] == rank[y]) { // the depth of the two trees are the same, increment the depth of the root
            rank[y]++;
        }
        return true;
    }
}

class Edge {
    int len, x, y;

    public Edge(int len, int x, int y) {
        this.len = len;
        this.x = x;
        this.y = y;
    }
}