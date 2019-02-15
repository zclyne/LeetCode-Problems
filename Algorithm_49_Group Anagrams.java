// 思路：对每一个strs中的String中的字母排序，消除anagram的不同字母顺序
// HashMap的键为排序后的字符串，值为该字符串对应的所有单词组成的List
// 遍历完成后，map的值集合即为答案

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] sortedStrCharArray = str.toCharArray();
            Arrays.sort(sortedStrCharArray);
            String sortedStr = String.valueOf(sortedStrCharArray);
            ArrayList<String> stringList = map.getOrDefault(sortedStr, new ArrayList<>());
            stringList.add(str);
            map.put(sortedStr, stringList);
        }
        return new ArrayList<List<String>>(map.values());
    }
}