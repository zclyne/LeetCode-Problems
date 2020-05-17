import java.util.Arrays;
import java.util.Comparator;

// 思路：Greedy Algorithm
// 先按照interval的结束时间从小到大排序
// 要删除的interval数量 = 原intervals的长度 - intervals中最大的没有overlap的interval数
// https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> {
            return a[1] - b[1];
        });
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}