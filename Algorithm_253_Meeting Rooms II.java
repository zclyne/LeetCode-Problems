import java.util.*;

// 方法：首先将intervals按照start从小到大的顺序排序，end从大到小
// 用最小堆记录当前的所有interval的end
// 遍历intervals，对于当前的interval，如果start大于堆顶元素
// 说明堆顶的end已经可以出队，不断从堆顶弹出元素直到堆为空或堆顶元素小于start为止
// 这表明堆顶元素对应的meeting还没有结束。将当前interval的end添加到堆中
// 堆的大小就代表当前正在进行的meeting总数
// result记录遍历过程中，堆的size的最大值，也就是最终的结果

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i2[1] - i1[1];
            }
            return i1[0] - i2[0];
        });

        int result = 1;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            while (!priorityQueue.isEmpty() && start >= priorityQueue.peek()) {
                priorityQueue.poll();
            }
            priorityQueue.offer(end);
            result = Math.max(result, priorityQueue.size());
        }
        return result;
    }
}