import java.util.PriorityQueue;

// 解法1：优先级队列
// 思路：构建一个Grid类代表grid中的每一个格子，这个类包含坐标i, j和格子的高度值value三个属性
// 并且Grid类实现了Comparable接口，用于在priorityQueue中进行排序
// 初始时，time = 0，pq中只包含grid[0][0]这一个元素
// status数组存储每个格子的状态，初始状态除了grid[0][0]都为0，表示还没有被添加到pq中
// status[i][j] == 1表示已经被添加到了pq中，status[i][j] == 2表示已经被访问过
// 外层循环判断条件为status[n - 1][n - 1] != 2，表示最右下角的格子还没有被访问过
// 内层循环不断从pq中拿出grid，如果这个grid的高度小于等于当前时间，说明可以游到这个格子
// 因此把status[i][j]置为2，然后对当前格子的上下左右四个格子，如果这些格子的status为0
// 就将其放入pq中，并且把status更新为1
// 如果pq中的头部元素的value > time，说明在当前时刻已经无法游到任何一个新的格子，因此把time++
// 进入下一轮外层循环

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Grid> pq = new PriorityQueue<>();
        pq.add(new Grid(0, 0, grid[0][0]));
        int[][] status = new int[n][n];
        status[0][0] = 1; // grid[0][0] is added to pq
        int time = 0;

        // status == 2 means visited
        while (status[n - 1][n - 1] != 2) {
            // pq.peek().value <= time means we can reach this grid now
            // remove it from the priority queue, and add the adjacent grids
            // to the pq if they are not added yet
            while (!pq.isEmpty() && pq.peek().value <= time) {
                Grid curGrid = pq.poll();
                int i = curGrid.i, j = curGrid.j;
                status[i][j] = 2;
                // up
                if (i > 0 && status[i - 1][j] == 0) {
                    pq.add(new Grid(i - 1, j, grid[i - 1][j]));
                    status[i - 1][j] = 1;
                }
                // down
                if (i < n - 1 && status[i + 1][j] == 0) {
                    pq.add(new Grid(i + 1, j, grid[i + 1][j]));
                    status[i + 1][j] = 1;
                }
                // left
                if (j > 0 && status[i][j - 1] == 0) {
                    pq.add(new Grid(i, j - 1, grid[i][j - 1]));
                    status[i][j - 1] = 1;
                }
                // right
                if (j < n - 1 && status[i][j + 1] == 0) {
                    pq.add(new Grid(i, j + 1, grid[i][j + 1]));
                    status[i][j + 1] = 1;
                }
            }
            // cannot reach any more grid now, increment time
            time++;
        }
        // because there is a time++ at the end of the outer while loop
        // so the result should be time - 1
        return time - 1;
    }
}

class Grid implements Comparable<Grid> {
    public int i;
    public int j;
    public int value;

    public Grid(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public int compareTo(Grid g) {
        return this.value - g.value;
    }
}

// 其他思路详见：https://leetcode-cn.com/problems/swim-in-rising-water/solution/shui-wei-shang-sheng-de-yong-chi-zhong-y-862o/

// 解法2：二分查找+遍历
public class Solution {

    private int N;

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        this.N = grid.length;

        int left = 0;
        // because the grid's height is within range [0, N * N - 1], so the initial value
        // of right can be selected as N * N - 1
        int right = N * N - 1;
        while (left < right) {
            // left + right 不会溢出
            int mid = (left + right) / 2;
            boolean[][] visited = new boolean[N][N];
            if (grid[0][0] <= mid && dfs(grid, 0, 0, visited, mid)) {
                // mid 可以，尝试 mid 小一点是不是也可以呢？下一轮搜索的区间 [left, mid]
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 使用深度优先遍历得到从 (x, y) 开始向四个方向的所有小于等于 threshold 且与 (x, y) 连通的结点
     *
     * @param grid
     * @param x
     * @param y
     * @param visited
     * @param threshold
     * @return
     */
    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited, int threshold) {
        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] <= threshold) {
                if (newX == N - 1 && newY == N - 1) {
                    return true;
                }

                if (dfs(grid, newX, newY, visited, threshold)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

// 解法3：并查集
public class Solution {

    private int N;

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        this.N = grid.length;

        int len = N * N;
        // 下标：方格的高度，值：对应在方格中的坐标
        int[] index = new int[len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index[grid[i][j]] = getIndex(i, j);
            }
        }

        UnionFind unionFind = new UnionFind(len);
        for (int i = 0; i < len; i++) {
            int x = index[i] / N;
            int y = index[i] % N;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY) && grid[newX][newY] <= i) {
                    unionFind.union(index[i], getIndex(newX, newY));
                }

                if (unionFind.isConnected(0, len - 1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getIndex(int x, int y) {
        return x * N + y;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int root(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean isConnected(int x, int y) {
            return root(x) == root(y);
        }

        public void union(int p, int q) {
            if (isConnected(p, q)) {
                return;
            }
            parent[root(p)] = root(q);
        }
    }
}