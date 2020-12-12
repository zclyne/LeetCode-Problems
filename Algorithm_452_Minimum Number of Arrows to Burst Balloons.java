import java.util.Arrays;
import java.util.Comparator;

// 思路：贪心法，本质上就是求interval的交集，存在交集的所有气球只需要一根箭就可以全部戳破
// 不存在交集的每一个气球都单独需要一根箭来戳破

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int numOfArrows = 1;
        // sort by the end coordinate of each balloon
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) { // has intersection
                continue;
            }
            numOfArrows++;
            end = points[i][1];
        }
        return numOfArrows;
    }
}