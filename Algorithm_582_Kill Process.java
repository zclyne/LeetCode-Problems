import java.util.*;

// 方法：用map保存图，然后BFS

class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = pid.size();
        for (int id : pid) {
            graph.put(id, new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int curProcessId = pid.get(i);
            int parentProcessId = ppid.get(i);
            if (parentProcessId != 0) {
                graph.get(parentProcessId).add(curProcessId);
            }
        }

        List<Integer> result = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            List<Integer> children = graph.get(cur);
            for (int childProcessId : children) {
                queue.offer(childProcessId);
            }
        }
        return result;
    }
}