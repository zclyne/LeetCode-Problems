import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 方法：先遍历adjacentPairs，把邻接关系存入map
// 然后遍历一遍map，找到只有一个neighbor的数字，则这个数字就是数组的起点
// 从这个起点开始，不断从map中得到当前数字的两个相邻数字
// 利用pre来进行去重，把两个相邻数字中，不等于pre的那个作为下一个cur
// 直到当前数字只有一个相邻数字，说明当前数字是数组的最后一个数字
// 退出循环并返回

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            int a = pair[0], b = pair[1];
            if (!neighbors.containsKey(a)) {
                neighbors.put(a, new ArrayList<>());
            }
            neighbors.get(a).add(b);
            if (!neighbors.containsKey(b)) {
                neighbors.put(b, new ArrayList<>());
            }
            neighbors.get(b).add(a);
        }

        // look for the starting node
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : neighbors.entrySet()) {
            if (entry.getValue().size() == 1) { // only has one neighbor, so it is the starting node
                start = entry.getKey();
                break;
            }
        }

        int pre = start;
        int cur = neighbors.get(start).get(0);
        int[] result = new int[neighbors.size()];
        result[0] = start;
        int idx = 1;
        while (idx < result.length) {
            result[idx++] = cur;
            List<Integer> neighbor = neighbors.get(cur);
            if (neighbor.size() == 1) { // cur is the ending node
                continue;
            }
            int newPre = cur;
            cur = neighbor.get(0) == pre ? neighbor.get(1) : neighbor.get(0);
            pre = newPre;
        }

        return result;
    }
}