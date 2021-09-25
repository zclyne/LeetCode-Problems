import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> resultList = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int curStart = intervals[0][0];
        int curMaxEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            if (curInterval[0] <= curMaxEnd) { // can merge
                curMaxEnd = Math.max(curMaxEnd, curInterval[1]);
            } else {
                resultList.add(new int[] {curStart, curMaxEnd});
                curStart = curInterval[0];
                curMaxEnd = curInterval[1];
            }
        }
        resultList.add(new int[] {curStart, curMaxEnd});
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = resultList.get(i)[0];
            result[i][1] = resultList.get(i)[1];
        }
        return result;
    }
}