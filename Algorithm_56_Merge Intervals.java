import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// Solution in Discuss, beats 92.42%
// 思路：拆开interval的start和end并排序后重新组合并不会影响merge后的interval
// merge后的interval范围只受到最小的start和最大的end的影响

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        List<Interval> res = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) { // j is start of interval
            if (i == n - 1 || starts[i + 1] > ends[i]) { // start[i + 1] > ends[i] means the two intervals don't overlap
                res.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }
        return res;
    }
}

// My Solution, beats 43.66%
// 思路：按照start对所有interval排序，并保存第一个interval的start和end作为当前合并后的interval的start和end
// 遍历排序后的intervals，若新的interval的start <= curEnd，说明可以合并，并取新的interval的end与curEnd中的较大值作为新的curEnd
// 否则，不能合并。用curStart和curEnd创建一个新的interval添加到res中作为一个结果

// class Solution {
//     public List<Interval> merge(List<Interval> intervals) {
//         List<Interval> res = new ArrayList<>();
//         if (intervals == null || intervals.size() == 0) {
//             return res;
//         }
//         intervals.sort((v1, v2) -> v1.start - v2.start);
//         int curStart = intervals.get(0).start, curEnd = intervals.get(0).end;
//         for (int i = 1; i < intervals.size(); i++) {
//             int newStart = intervals.get(i).start, newEnd = intervals.get(i).end;
//             if (newStart <= curEnd) { // can merge
//                 curEnd = Math.max(curEnd, newEnd);
//             } else { // cannot merge
//                 res.add(new Interval(curStart, curEnd));
//                 curStart = newStart;
//                 curEnd = newEnd;
//             }
//         }
//         res.add(new Interval(curStart, curEnd));
//         return res;
//     }
// }