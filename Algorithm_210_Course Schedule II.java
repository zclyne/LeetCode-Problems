import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// My Solution
// 思路：BFS。先调用makeGraph方法建立每一门课程及其后继课程的关系，并用数组inDegree记录每一门课的入度，也就是先修课数量
// 然后把所有入度为0（即可以选的课程）都添加到队列availableCourses中，进入循环
// 每次从队头取出一门课，遍历其所有后继课程，每一门后继课程的入度都-1，表示少了一门先修课
// 当后继课程的入度减到0时，就可以开始上这门课了，把它添加到availableCourses的尾部
// 变量cur记录当前在result中要放入的位置。在while退出后，如果cur == numCourses，表明所有课都修完了，直接返回result
// 若不等，说明存在环路，返回空数组

class Solution {

    private int[] inDegree;
    private ArrayList<Integer>[] graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        inDegree = new int[numCourses];
        graph = new ArrayList[numCourses];
        makeGraph(numCourses, prerequisites);

        Queue<Integer> availableCourses = new LinkedList<>();
        int[] result = new int[numCourses];
        int cur = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // course i doesn't have any prerequisites
                availableCourses.offer(i);
            }
        }
        while (!availableCourses.isEmpty()) {
            int curCourse = availableCourses.poll();
            result[cur++] = curCourse;
            ArrayList<Integer> nextCourses = graph[curCourse];
            if (nextCourses != null) {
                for (int nextCourse : nextCourses) {
                    if (--inDegree[nextCourse] == 0) { // all prerequisites satisfied, can take nextCourse
                        availableCourses.offer(nextCourse);
                    }
                }
            }
        }
        return cur == numCourses ? result : new int[0]; // if cur == numCourses, it means that all courses can be finished
    }

    private void makeGraph(int numCourses, int[][] prerequisites) {
        for (int[] coursePair : prerequisites) {
            int course = coursePair[0], prerequisite = coursePair[1];
            inDegree[course]++;
            if (graph[prerequisite] == null) {
                graph[prerequisite] = new ArrayList<>();
            }
            graph[prerequisite].add(course);
        }
    }

}


// DFS Solution
// 思路：若DFS过程中发现形成了环，则表明无法修完全部课程，返回空数组

class DFSSolution {
    // dfs
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build graph & init graph
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);

        // start w/ any node
        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];

        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited[i]) continue;
            boolean cycle = dfs(i, graph, visited, onPath, order);
            if (cycle) return new int[0];
        }

        // list to int[]
        int[] res = new int[order.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = order.get(i);
        }
        return res;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] preq : prerequisites) {
            graph.get(preq[1]).add(preq[0]);
        }
        return graph;
    }

    private boolean dfs(int course, List<List<Integer>> graph, boolean[] visited, boolean[] onPath, List<Integer> order) {
        if (visited[course]) return false; // node visited
        visited[course] = true;
        onPath[course] = true;

        for (int neighbor : graph.get(course)) {
            if (onPath[neighbor]) return true; // cycle detected
            boolean cycle = dfs(neighbor, graph, visited, onPath, order);
            if (cycle) return true;
        }

        order.add(0, course); // must add course at the front of order
        // remove from onPath
        onPath[course] = false;

        return false;
    }
}