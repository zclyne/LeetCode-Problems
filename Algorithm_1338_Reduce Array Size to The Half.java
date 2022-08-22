import java.util.*;

// 方法1：map + heap
// 先用map存下arr中每个元素出现的次数
// 然后输入到最大堆中，再逐个pop直到pop的元素出现次数之和大于len的一半

class Solution {
    public int minSetSize(int[] arr) {
        int halfLen = arr.length / 2;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int count : counts.values()) {
            pq.offer(count);
        }

        int curCount = 0, result = 0;
        while (curCount < halfLen) {
            curCount += pq.poll();
            result++;
        }
        return result;
    }
}

// 方法2：map + counting sort
// 先用map存下arr中每个元素出现的次数
// 然后将每个出现次数的个数记录在数组appearTimes中
// 再反向遍历appearTimes直到删除的元素个数大于等于长度的一半

class Solution2 {
    public int minSetSize(int[] arr) {
        int halfLen = arr.length / 2;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        int[] appearTimes = new int[arr.length + 1];
        for (int count : counts.values()) {
            appearTimes[count]++;
        }
        int curCount = 0, result = 0;
        for (int i = appearTimes.length - 1; i >= 0 && curCount < halfLen; i--) {
            while (appearTimes[i] > 0 && curCount < halfLen) {
                curCount += i;
                result++;
                appearTimes[i]--;
            }
        }
        return result;
    }
}