import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 二刷BFS解法
// 队列中保存的不是单词本身，而是整个路径上的所有单词，通过逗号分隔
// 这样，BFS路径上的所有单词都被自动保存下来了

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        int shortestPathLength = 0;

        while (!queue.isEmpty()) {
            String str = queue.poll();
            String[] words = str.split(",");
            String curWord = words[words.length - 1];
            visited.add(curWord);

            // change each letter and see whether the new word exists in dict
            for (int i = 0; i < curWord.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    char c = (char) ('a' + j);
                    String newWord = curWord.substring(0, i) + c + curWord.substring(i + 1);
                    if (newWord.equals(endWord)) { // found an valid path to the endWord
                        if (shortestPathLength == 0 || words.length + 1 == shortestPathLength) { // path not found before, this is the shortest path
                            List<String> path = new ArrayList<>();
                            path.addAll(Arrays.asList(words));
                            path.add(endWord);
                            result.add(path);
                            shortestPathLength = path.size();
                        } else { // not the shortest path, no need to continue the BFS
                            return result;
                        }
                    } else if (dict.contains(newWord) && !visited.contains(newWord)) { // newWord exists in dict and is not visited before
                        String newStr = str + ',' + newWord; // append the newWord to the string so that the path is automatically saved
                        queue.offer(newStr);
                    }
                }
            }
        }

        return result;
    }
}

// BFS + DFS Solution
// 思路：先用BFS来保存从beginWord到wordList中的各个单词的距离，记录在distance中
// 并在BFS的过程中，计算wordList中的每个单词的在wordList中的相邻单词，记录在nodeNeighbors中
// 因为是BFS，所以第一次遇到endWord时，此时的距离就是最短的距离
// 然后再使用DFS来寻找具体的变化路径，要判断下一个单词的距离是否是当前单词的距离+1，若是，才继续递归
// 这样可以避免递归回到已经访问过的单词上

class Solution {
    private Set<String> set;
    private List<List<String>> result = new ArrayList<>();
    private Map<String, List<String>> nodeNeighbors = new HashMap<>();
    private Map<String, Integer> distance = new HashMap<>();


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return result;
        }
        distance.put(beginWord, 0);
        BFS(beginWord, endWord);
        DFS(beginWord, endWord, new ArrayList<>());
        return result;
    }

    private void BFS(String beginWord, String endWord) {
        boolean foundEnd = false;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                int curDistance = distance.get(curWord);
                List<String> neighbors = getNeighbors(curWord);
                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDistance + 1);
                        if (neighbor.equals(endWord)) {
                            foundEnd = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            if (foundEnd) {
                break;
            }
        }
    }

    // return all the neighbors of word in wordList. A neighbor is away from word by only one char
    private List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char oldChar = word.charAt(i);
            for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                if (newChar == oldChar) {
                    continue;
                }
                String newWord = word.substring(0, i) + newChar + word.substring(i + 1);
                if (set.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
        }
        nodeNeighbors.put(word, neighbors);
        return neighbors;
    }

    private void DFS(String curWord, String endWord, List<String> steps) {
        steps.add(curWord);
        if (curWord.equals(endWord)) {
            result.add(new ArrayList<>(steps));
        } else {
            // it's possible that nodeNeighbors.get(curWord) is null, so we must use getOrDefault
            // to avoid NullPointerException
            List<String> neighbors = nodeNeighbors.getOrDefault(curWord, new ArrayList<>());
            for (String neighbor : neighbors) {
                if (distance.get(neighbor) == distance.get(curWord) + 1) {
                    DFS(neighbor, endWord, steps);
                }
            }
        }
        steps.remove(steps.size() - 1);
    }
}

// My DFS Solution, Time Limit Exceeded

class MyDFSSolution {
    private int minStepCount = Integer.MAX_VALUE;
    private Set<String> set;
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return result;
        }
        List<String> steps = new ArrayList<>();
        steps.add(beginWord);
        helper(beginWord, endWord, steps);
        return result;
    }

    private void helper(String curWord, String endWord, List<String> steps) {
        if (steps.size() > minStepCount) { // steps must not be an answer
            return;
        }
        if (curWord.equals(endWord)) {
            if (steps.size() < minStepCount) {
                result.clear();
                minStepCount = steps.size();
            }
            result.add(new ArrayList<>(steps));
            return;
        }
        // change curWord by a char
        for (int i = 0; i < curWord.length(); i++) {
            char oldChar = curWord.charAt(i);
            for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                if (newChar == oldChar) {
                    continue;
                }
                String newWord = curWord.substring(0, i) + newChar + curWord.substring(i + 1);
                if (!set.contains(newWord)) {
                    continue;
                } else {
                    set.remove(newWord);
                    steps.add(newWord);
                    helper(newWord, endWord, steps);
                    steps.remove(steps.size() - 1);
                    set.add(newWord);
                }
            }
        }
    }
}