import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 思路：使用TreeMap
// ceilingKey()方法返回大于等于key的最小的key，若不存在则返回null

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }
        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i][1]);
            result[i] = key == null ? -1 : map.get(key);
        }
        return result;
    }
}

// 解法二：二分查找
// 先用map把intervals中interval和下标的对应关系保存下来，然后对intervals按照start从小到大排序
// 对排序后的intervals中的每一个interval，使用二分查找的方法回到intervals中找到一个interval，其start
// 大于等于当前interval的end

class BinarySearchSolution {
    public int[] findRightInterval(int[][] intervals) {
        // store the index of each interval
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            indexMap.put(intervals[i][0], i);
        }

        // sort intervals according to the start of the interval
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // look for interval whose start is greater than or equal to the end of the current interval
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int tmpIdx = binarySearch(intervals, intervals[i][1]);
            int curIdx = indexMap.get(intervals[i][0]);
            if (tmpIdx == intervals.length) {
                result[curIdx] = -1;
            } else {
                result[curIdx] = indexMap.get(intervals[tmpIdx][0]);
            }
        }
        return result;
    }

    // look for the interval whose start is greater than or equal to num, and return its idx in intervals
    // if such an interval doesn't exist. return interval.length 
    private int binarySearch(int[][] intervals, int num) {
        int left = 0, right = intervals.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (intervals[mid][0] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}