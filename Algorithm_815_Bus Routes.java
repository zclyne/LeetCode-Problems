import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// 解法：BFS
// 对站进行BFS，用set busesAlreadyTaken存储已经坐过的公交车
// 对于到达当前站curStop的所有公交车，如果是一部还没有坐过的公交车
// 就将这部公交车能够到达的除当前站以外的所有站都添加到队列中
// 注意BFS是分层的，一层就代表坐过的公交车数量
// 当一层遍历完毕后，busCount++，代表需要换乘到一辆新的公交车上，开始下一层BFS

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // initialization
        Map<Integer, ArrayList<Integer>> stopToBuses = new HashMap<>();
        for (int bus = 0; bus < routes.length; bus++) {
            int[] route = routes[bus];
            for (int stop : route) {
                ArrayList<Integer> buses = stopToBuses.getOrDefault(stop, new ArrayList<>());
                buses.add(bus);
                stopToBuses.put(stop, buses);
            }
        }

        if (source == target) {
            ArrayList<Integer> buses = stopToBuses.get(source);
            return buses.size() == 0 ? -1 : 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> busesAlreadyTaken = new HashSet<>();
        queue.offer(source);
        int busCount = 1;

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int curStop = queue.poll();
                ArrayList<Integer> buses = stopToBuses.getOrDefault(curStop, new ArrayList<>());
                for (int bus : buses) {
                    if (busesAlreadyTaken.contains(bus)) { // already taken this bus, skip it
                        continue;
                    }
                    busesAlreadyTaken.add(bus);
                    int[] stopsOfCurBus = routes[bus];
                    for (int stop : stopsOfCurBus) {
                        if (stop == target) {
                            return busCount;
                        } else if (stop == curStop) {
                            continue;
                        }
                        queue.offer(stop);
                    }
                }
            }
            busCount++;
        }

        return -1;
    }
}