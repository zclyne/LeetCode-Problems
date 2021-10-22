import java.util.*;

// 方法：先遍历words，记录各个word到其对应的frequency的映射，存在map中
// 然后从map反向构建出frequency到这个frequency对应的所有words的映射，存在list中
// 为了维护具有相同frequency的words的字典序关系，使用PriorityQueue作为list的元素类型
// 最后，按照frequency从高到低的顺序遍历list，将word逐个填充到result中，直到result的size为k

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        int maxFreq = 1;
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            int count = wordCountMap.getOrDefault(word, 0);
            wordCountMap.put(word, count + 1);
            maxFreq = Math.max(maxFreq, count + 1);
        }
        List<PriorityQueue<String>> freqToWords = new ArrayList<>();
        for (int i = 0; i <= maxFreq; i++) {
            freqToWords.add(new PriorityQueue<>());
        }
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();
            PriorityQueue<String> pq = freqToWords.get(freq);
            pq.offer(word);
        }

        int i = maxFreq;
        List<String> result = new ArrayList<>();
        while (result.size() < k) {
            PriorityQueue<String> pq = freqToWords.get(i);
            while (result.size() < k && !pq.isEmpty()) {
                result.add(pq.poll());
            }
            i--;
        }

        return result;
    }
}