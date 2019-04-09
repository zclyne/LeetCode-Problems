import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.HashMap;

// Better Solution: Bucket Sort. beats 99.44%
// 思路：先用map存储nums中所有数字及其对应的出现次数，然后根据各个数的frequency把数字存入相应的bucket中，bucket[i]是一个ArrayList，存放所有出现次数为i次的数字
// 最后对bucket做反向遍历（即频率从高到低），将bucket[i]中的所有数字加入res中，直到已经加入了k个数字为止

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer>[] bucket = new ArrayList[nums.length + 1]; // cannot be ArrayList<>[nums.length + 1] or ArrayList<Integer>[nums.length + 1], which are not allowed by java
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int freq = map.getOrDefault(num, 0);
            map.put(num, freq + 1);
        }
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), freq = entry.getValue();
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(num);
        }
        for (int i = nums.length; i >= 0 && k > 0; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
                k -= bucket[i].size();
            }
        }
        return res;
    }
}


// My Solution, beats 42.53%
// 思路：先用map存储nums中所有数字及其对应的出现次数，然后将各个entry插入到PriorityQueue中，该PriorityQueue按照entry的出现频率从高到低排列
// 最后，从队列中取出前k个元素，即所要的k个出现频率最高的数字

class MySolution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int freq = map.getOrDefault(num, 0);
            map.put(num, freq + 1);
        }
        PriorityQueue<Entry<Integer, Integer>> queue = new PriorityQueue<>(map.size(), (pair1, pair2) -> {return pair2.getValue() - pair1.getValue();}); // use pq to order by frequency, from higher to lower
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        for (int i = 0; i < k; i++) {
            res.add(queue.poll().getKey());
        }
        return res;
    }
}