import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// 二刷。回溯法

class Solution {
    
    private List<String> result;
    private Set<String> dict;

    public List<String> wordBreak(String s, List<String> wordDict) {
        result = new ArrayList<>();
        dict = new HashSet<>();
        dict.addAll(wordDict);
        backtracking(s, 0, new ArrayList<>());
        return result;
    }
    
    private void backtracking(String s, int start, List<String> curWords) {
        if (start == s.length()) { // already travarsed the whole string
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < curWords.size(); i++) {
                stringBuilder.append(curWords.get(i));
                if (i != curWords.size() - 1) {
                    stringBuilder.append(' ');
                }
            }
            result.add(stringBuilder.toString());
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (dict.contains(word)) { // is a valid word
                curWords.add(word);
                backtracking(s, i, curWords);
                curWords.remove(curWords.size() - 1);
            }
        }
    }
}

// 思路：先把wordDict存储到set中方便查询，然后使用DFS
// 为了避免TLE，用map来记录字符串和该字符串所能够拆分的所有情况的映射
// 如果当前的s已经保存在了map中，则直接以s为key从map中取出结果并返回
// buxuyao zaicijisuan 

class Solution {

    private Set<String> words = new HashSet<>();
    private Map<String, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        words.addAll(wordDict);
        return DFS(s);
    }

    private List<String> DFS(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> result = new ArrayList<>();
        if (words.contains(s)) { // s itself is a valid setence
            result.add(s);
        }
        for (int end = 1; end <= s.length(); end++) {
            String word = s.substring(0, end);
            if (words.contains(word)) {
                List<String> nextStrings = DFS(s.substring(end));
                for (String nextStr : nextStrings) {
                    result.add(word + " " + nextStr);
                }
            }
        }
        map.put(s, result);
        return result;
    }

}