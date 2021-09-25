import java.util.HashMap;
import java.util.Map;
import java.util.List;

// 方法：用map存储wall上每两块砖之间的接缝位置，以及接缝出现的次数
// wall本身的高度（即wall.size()）与接缝出现次数的最大值之差就是所穿过的砖的数量的最小值

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int totalWidth = 0;
        for (List<Integer> bricks : wall) {
            int curWidth = 0;
            for (int j = 0; j < bricks.size(); j++) {
                curWidth += bricks.get(j);
                int curCount = map.getOrDefault(curWidth, 0);
                map.put(curWidth, curCount + 1);
            }
            totalWidth = Math.max(totalWidth, curWidth);
        }
        map.remove(totalWidth);
        int maxCount = 0;
        for (int count : map.values()) {
            maxCount = Math.max(maxCount, count);
        }
        return wall.size() - maxCount;
    }
}