import java.util.List;
import java.util.ArrayList;

// 思路：Sliding Window。先把p中所有字符出现的次数存入数组map，然后开始遍历s，变量left和right控制window的左边界和右边界
// 变量count维护p中有多少字符已经被使用
// map[s[right++]]--，若--之前的map[s[right]] > 0，表明s[right]是p中的字符，count--
// 当count减到0时，说明p中所有字符都被使用，则找到了一个满足条件的annagram，res.add(left)
// 当right - left == p.length()时，说明window的大小恰好等于p的长度，window不可能进一步变大
// 因此要把left向右移动，同时把map[s[left]]++。若++后的map[s[left]] > 0，说明原本s[left]是p中的字符，现在要还回去
// 因此count++

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] map = new int[26];
        for (int i = 0; i < p.length(); i++) {
            map[p.charAt(i) - 'a']++;
        }
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            if (map[s.charAt(right++) - 'a']-- > 0) { // s[right] is contained in p
                count--;
            }
            if (count == 0) { // count == 0 means we successfully find an anagram
                res.add(left);
            }
            if (right - left == p.length() && ++map[s.charAt(left++) - 'a'] > 0) { // sliding window reaches the maximum size, and s[left] is contained in p
                count++;
            }
        }
        return res;
    }
}