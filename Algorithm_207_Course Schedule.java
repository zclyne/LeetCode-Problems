import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution 1: BFS
// 思路：courseGraph存储各个课程的后继课程，inDegrees存储各个课程的入度，即先修课数量
// 首先调用makeGraph建立courseGraph和inDegrees，然后对inDegrees遍历一遍，把所有入度为0的课程加入队列
// 入度为0的课即为当前可以学习的课
// 循环从队列中取出课程cur，并将该课程的所有后继课程的入度-1。若-1后的入度变为0，说明该课程的所有先修课都已修齐，可以学习
// 因此加入队列中。变量count记录已经修过的课程数
// 当跳出循环时，如果count == numCourses，说明所有课程都学完，返回true；否则返回false

class Solution {
    private ArrayList<ArrayList<Integer>> courseGraph = new ArrayList<>();
    private int[] inDegrees;
    private Queue<Integer> availableCourses;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            courseGraph.add(new ArrayList<>());
        }
        inDegrees = new int[numCourses];
        availableCourses = new LinkedList<Integer>();
        makeGraph(prerequisites);
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) { // can take this course, add it to the queue
                availableCourses.offer(i);
            }
        }

        while (!availableCourses.isEmpty()) {
            count++;
            int cur = availableCourses.poll();
            ArrayList<Integer> nextCourses = courseGraph.get(cur);
            for (Integer course : nextCourses) {
                if (--inDegrees[course] == 0) {
                    availableCourses.offer(course);
                }
            }
        }
        return count == numCourses;
    }

    public void makeGraph(int[][] prerequisites) {
        for (int[] coursePair : prerequisites) {
            courseGraph.get(coursePair[1]).add(coursePair[0]);
            inDegrees[coursePair[0]]++; // coursePair[0] has a prerequisite, so inDegree++
        }
    }
}

// Solution 2: DFS
// 思路：建图步骤与bfs中相同。不能修完全部课程等价于图中有环
// visited维护整个便利过程中访问过的课程，curVisited维护当前DFS中访问过的课程
// 若在DFS过程中遇到了一个已经访问过的课程，表明有环路形成，返回false
// 无环路，则返回true

class Solution2 {
    private ArrayList<ArrayList<Integer>> courseGraph = new ArrayList<>();
    private boolean[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            courseGraph.add(new ArrayList<>());
        }
        visited = new boolean[numCourses];
        makeGraph(prerequisites);

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && !DFS(i, new boolean[numCourses])) {
                return false;
            }
        }
        return true;
    }

    public void makeGraph(int[][] prerequisites) {
        for (int[] coursePair : prerequisites) {
            courseGraph.get(coursePair[1]).add(coursePair[0]);
        }
    }

    public boolean DFS(int curCourse, boolean[] curVisited) {
        if (curVisited[curCourse]) { // a cycle is detected
            return false;
        }
        curVisited[curCourse] = visited[curCourse] = true;
        ArrayList<Integer> nextCourses = courseGraph.get(curCourse);
        for (Integer nextCourse: nextCourses) {
            if (!DFS(nextCourse, curVisited)) {
                return false;
            }
        }
        curVisited[curCourse] = false; // don't forget this step
        return true;
    }
}