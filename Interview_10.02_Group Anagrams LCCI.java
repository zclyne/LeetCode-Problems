import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 方法1：对于strs中的每个单词都排序，然后以排序后的单词为key，单词列表为value，存入map sortedToWords
// 最终，sortedToWords.values()就是要求的结果

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> sortedToWords = new HashMap<>();
        for (String word : strs) {
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            String sorted = String.valueOf(wordChars);
            if (!sortedToWords.containsKey(sorted)) {
                sortedToWords.put(sorted, new ArrayList<>());
            }
            sortedToWords.get(sorted).add(word);
        }
        List<List<String>> result = new ArrayList<>(sortedToWords.values());
        return result;
    }
}

// 方法2：将方法1中的排序更改为对字母计数，其他部分相同