import java.util.ArrayList;

// more straight-forward and concise solution
// 思路：按顺序遍历intervals，先加入在newInterval左侧的不重叠的interval
// 再找出所有的重叠interval，用重叠后的start和end更新newInterval的start和end
// 最后加入newInterval右侧的不重叠的interval

class BetterSolution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resultList = new ArrayList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            resultList.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }
        // merge all overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        resultList.add(new int[] {newInterval[0], newInterval[1]});
        // add all the intervals starting after newInterval ends
        while (i < intervals.length) {
            resultList.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }
        int[][] result = new int[resultList.size()][2];
        for (int j = 0; j < resultList.size(); j++) {
            result[j][0] = resultList.get(j)[0];
            result[j][1] = resultList.get(j)[1];
        }
        return result;
    }
}

// My Solution
// 思路：两次二分查找，分别找到newInterval[0]对应的插入位置和newInterval[1]对应的插入位置
// 然后判断左右是否需要merge，若merge，相应地调整start和end的值
// 最后，先加入deleteStart左侧的所有interval，再加入新的merge完的interval，最后加入deleteEnd右侧的所有interval

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // look for where newInterval[0] should be
        int n = intervals.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid][0] >= newInterval[0]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int start = left;
        // look for where newInterval[1] should be
        left = 0; right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid][1] <= newInterval[1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int end = right;
        boolean leftShouldMerge = start > 0 && intervals[start - 1][1] >= newInterval[0] ? true : false;
        boolean rightShouldMerge = end < n - 1 && intervals[end + 1][0] <= newInterval[1] ? true : false;
        int newStart = leftShouldMerge ? intervals[start - 1][0] : newInterval[0];
        int newEnd = rightShouldMerge ? intervals[end + 1][1] : newInterval[1];
        int deleteStart = leftShouldMerge ? start - 1 : start;
        int deleteEnd = rightShouldMerge ? end + 1 : end;
        int[][] result = new int[n - (deleteEnd - deleteStart)][2];
        int pos = 0;
        for (int i = 0; i < deleteStart; i++) {
            result[pos][0] = intervals[i][0];
            result[pos][1] = intervals[i][1];
            pos++;
        }
        result[pos][0] = newStart;
        result[pos][1] = newEnd;
        pos++;
        for (int i = deleteEnd + 1; i < n; i++) {
            result[pos][0] = intervals[i][0];
            result[pos][1] = intervals[i][1];
            pos++;
        }
        return result;
    }
}