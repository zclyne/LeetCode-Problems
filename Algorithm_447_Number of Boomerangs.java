import java.util.*;

// 对每一个点i，将其作为tuple的第一个点
// 然后计算点i到其他点的距离，并将距离到对应的pair的个数的映射记录在map中
// 如果相同距离对应的pair数大于等于2，则说明可以形成boomerang
// 若有count个这样的pair，则由排列数公式，boomerang个数为count * (count - 1)

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int result = 0;

        // compute the square of distances between the i-th point
        // and any other point, and store the distance in a hashmap
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int[] point1 = points[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int[] point2 = points[j];
                int dist = (point1[0] - point2[0]) * (point1[0] - point2[0]) + 
                           (point1[1] - point2[1]) * (point1[1] - point2[1]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (int count : map.values()) {
                result += count * (count - 1);
            }
        }

        return result;
    }
}