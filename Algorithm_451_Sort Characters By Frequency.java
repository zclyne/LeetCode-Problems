import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 方法1：用map存储每个字符出现的次数
// 然后将字符本身和其出现的次数构成类Counter的对象，放入大顶堆中
// 最后从大顶堆中逐个pop出所有元素，构造字符串

class Solution {

    class Counter implements Comparable<Counter> {
        public char c;
        public int freq;

        Counter(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Counter counter) {
            if (this.freq != counter.freq) {
                return counter.freq - this.freq; // sort from the greatest to the smallest
            }
            return this.c - counter.c; // sort by lexicographical order
        }
    }

    public String frequencySort(String s) {
        Map<Character, Integer> counterMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            counterMap.put(c, counterMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Counter> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : counterMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            Counter counter = new Counter(c, count);
            pq.offer(counter);
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!pq.isEmpty()) {
            Counter counter = pq.poll();
            for (int i = 0; i < counter.freq; i++) {
                stringBuilder.append(counter.c);
            }
        }

        return stringBuilder.toString();
    }
}



// 方法2：桶排序
// 由于每个字符在字符串中出现的频率存在上限，因此可以使用桶排序的思想，根据出现次数生成排序后的字符串。具体做法如下：
// 1. 遍历字符串，统计每个字符出现的频率，同时记录最高频率maxFreq；
// 2. 创建桶，存储从1到maxFreq的每个出现频率的字符；
// 3. 按照出现频率从大到小的顺序遍历桶，对于每个出现频率，获得对应的字符，然后将每个字符按照出现频率拼接到排序后的字符串。

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxFreq = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
            maxFreq = Math.max(maxFreq, frequency);
        }
        StringBuffer[] buckets = new StringBuffer[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new StringBuffer();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = maxFreq; i > 0; i--) {
            StringBuffer bucket = buckets[i];
            int size = bucket.length();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket.charAt(j));
                }
            }
        }
        return sb.toString();
    }
}