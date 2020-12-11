// 思路：用map记录下之前出现过的字符及其出现的位置，变量curStart记录当前substring的起始位置
// 当碰到重复字符时，从map中得到这个字符上一次出现的位置，并与当前的起始位置做比较（重要，考虑"abba"）
// 取两者中较大的作为新的起始位置，这样就可以保证curStart与i之间始终没有重复字符出现
// i - curStart + 1是当前的substring的长度，当它大于maxLen时，更新maxLen

import java.util.HashMap;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int curStart = 0, maxLen = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (map.containsKey(curChar)) { // repeat
                curStart = Math.max(curStart, map.get(curChar) + 1); // notice this Math.max()
            }
            map.put(curChar, i);
            maxLen = Math.max(maxLen, i - curStart + 1);
        }
        return maxLen;
    }
}

// My second solution

class MySecondSolution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> seen = new HashSet<>();
        int left = 0, right = 0, result = 0;
        while (right < s.length()) {
            while (right < s.length() && !seen.contains(s.charAt(right))) {
                result = Math.max(result, right - left + 1);
                seen.add(s.charAt(right));
                right++;
            }
            if (right < s.length()) {
                while (left < right && s.charAt(left) != s.charAt(right)) {
                    seen.remove(s.charAt(left));
                    left++;
                }
                left++; right++;
            }
        }
        return result;
    }
}