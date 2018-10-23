// 思路：将words中以a, b, ..., z开头的单词存入map中，键为首字母，值为该单词本身
// 对S做遍历，每次取出一个字符，将原map中以该字母开头的所有单词的首字母去掉
// 若得到的新单词为空，说明该单词是S的一个subsequence，count++
// 若不为空，将新单词根据其首字母再次放入map中
// 最后返回count

import java.util.ArrayList;
import java.util.HashMap;

// faster solution
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.putIfAbsent(c, new LinkedList<String>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).addLast(word);
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            Deque<String> queue = map.get(c);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.removeFirst();
                if (word.length() == 1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).addLast(word.substring(1));
                } 
            }
        }
        return count;
    }
}

// My solution
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        ArrayList<ArrayList<Integer> > indices = new ArrayList<>(Collections.nCopies(26, null));
        for (int i = 0; i < S.length(); i++)
        {
            int idx = S.charAt(i) - 'a';
            if (indices.get(idx) == null)
            {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                indices.set(idx, tmp);
            }
            else indices.get(idx).add(i);
        }
        for (String word : words)
        {
            int lastIdx = -1, i = 0;
            for (; i < word.length(); i++)
            {
                int idx = word.charAt(i) - 'a';
                if (indices.get(idx) == null) break; // word.charAt(i) is not in S, cannot be subsequence
                else
                {
                    ArrayList<Integer> tmp = indices.get(idx);
                    int j = 0;
                    for (; j < tmp.size(); j++) if (tmp.get(j) > lastIdx) break;
                    if (j == tmp.size()) break; // invalid
                    else lastIdx = tmp.get(j);
                }
            }   
            if (i == word.length()) res++; // is subsequence
        }
        return res;
    }
}