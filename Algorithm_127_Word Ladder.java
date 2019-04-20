import java.util.List;
import java.util.HashSet;

// 思路：words记录还没有访问过的所有word，visited记录上一次变换得到的所有word
// 遍历visited，对它内部的每一个位置上的字母都从'a'到'z'变换，并查看变换后的word是否在words中
// 若存在，就把变换后的word加入tempVisited中，并把该word从words中删除，避免重复访问
// 一轮遍历完成后，若tempVisited的大小为0，说明无法走到endWord，直接返回0
// 否则，把tempVisited赋给visited，继续循环，直到visited中包含endWord为止。此时distance就是要求的结果

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        int distance = 1;
        while (!visited.contains(endWord)) {
            HashSet<String> tempVisited = new HashSet<>();
            for (String word : visited) {
                for (int i = 0; i < word.length(); i++) {
                    char[] wordArr = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArr[i] = ch;
                        String tempWord = new String(wordArr);
                        if (words.contains(tempWord)) {
                            tempVisited.add(tempWord);
                            words.remove(tempWord);
                        }
                    }
                }
            }
            if (tempVisited.size() == 0) return 0; // cannot reach endWord
            distance++;
            visited = tempVisited;
        }
        return distance;
    }
}