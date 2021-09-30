import java.util.*;

// 方法：构建图，然后使用BFS拓扑排序
// Map<Character, Set<Character>> graph存储字母之间的相对顺序信息
// value的set中的所有字母顺序都在key的字母之后
// Map<Character, Integer> inDegree记录各个字母的入度信息，即有多少字母需要排在这个字母之前
// 构建graph的步骤是：遍历words，不断比较两相邻word的第一个不同的字母，记为c1, c2
// 则c2需要排在c1之后，并把c2的入度++
// 找到后，需要从for循环中break出来，因为后续的字符不一定按顺序排列
// 图构建完毕后，从inDegree中找到入度为0的所有字母，他们需要排在最前面，插入队列中
// 不断从队列头部取出字母，将这个字母加入result，并将其后续的字母入度--
// 如果入度减到了0，则将其加入队列
// 直到最终队列为空。这一步是拓扑排序
// 最终，如果result的长度等于所有字母的个数（即graph.size()或inDegree.size()），则说明result是valid的，将其返回
// 否则，返回空字符串

class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // initialize
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new HashSet<>());
                inDegree.put(c, 0);
            }
        }

        // build the graph and inDegree map
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            if (cur.length() > next.length() && cur.startsWith(next)) { // case like ["abc", "ab"], invalid
                return "";
            }
            int minLength = Math.min(cur.length(), next.length());
            for (int j = 0; j < minLength; j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> nextChars = graph.get(c1);
                    if (!nextChars.contains(c2)) {
                        nextChars.add(c2);
                        inDegree.compute(c2, (k, v) -> v + 1);
                    }
                    break; // important
                }
            }
        }

        // BFS
        StringBuilder stringBuilder = new StringBuilder();
        Deque<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            stringBuilder.append(c);
            Set<Character> nextChars = graph.get(c);
            for (char nextC : nextChars) {
                int newDegree = inDegree.compute(nextC, (k, v) -> v - 1);
                if (newDegree == 0) { // can be added to the queue
                    queue.offer(nextC);
                }
            }
        }

        return stringBuilder.length() == graph.size() ? stringBuilder.toString() : "";
    }
}