// 思路：https://leetcode-cn.com/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
// 把二维数组看做一个具有m * n个节点的图，节点之间边的权重等于节点间高度之差的绝对值

// 方法1：二分查找 + BFS
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // 999999 = 10^6 - 1, which is the range of the height
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            // can reach the destination
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}

// 方法2：并查集
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<int[]>();
        // construct graph
        // the struct of edge is [start, end, weight]
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            uf.unite(x, y);
            if (uf.connected(0, m * n - 1)) { // check whether the starting point and the destination is connected
                ans = v;
                break;
            }
        }
        return ans;
    }
}

// 并查集模板
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

// 方法3：Dijkstra
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        
        return dist[m * n - 1];
    }
}


// 方法4：DFS，无法通过test，TLE
class Solution {
    private int result = Integer.MAX_VALUE;
    public int minimumEffortPath(int[][] heights) {
        // for the first grid, no effort required, so lastHeight == curHeight == heights[0][0]
        DFS(heights, 0, 0, 0, heights[0][0]);
        return this.result;
    }

    private void DFS(int[][] heights, int i, int j, int maxHeightDiffAbs, int lastHeight) {
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length) { // index out of bound
            return;
        } else if (heights[i][j] < 0) { // heights[i][j] has already been visited for the current path
            return;
        }

        // compute height difference
        int curHeight = heights[i][j];
        int heightDiffAbs = Math.abs(lastHeight - curHeight);
        maxHeightDiffAbs = Math.max(maxHeightDiffAbs, heightDiffAbs);

        // reach the destination
        if (i == heights.length - 1 && j == heights[0].length - 1) {
            this.result = Math.min(this.result, maxHeightDiffAbs);
            return;
        }

        // mark heights[i][j] as visited
        heights[i][j] = -heights[i][j];

        // up
        DFS(heights, i - 1, j, maxHeightDiffAbs, curHeight);
        // down
        DFS(heights, i + 1, j, maxHeightDiffAbs, curHeight);
        // left
        DFS(heights, i, j - 1, maxHeightDiffAbs, curHeight);
        // right
        DFS(heights, i, j + 1, maxHeightDiffAbs, curHeight);

        // mark heights[i][j] as not visited
        heights[i][j] = -heights[i][j];
    }
}