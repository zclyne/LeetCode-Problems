// 思路：数组countChar记录s中各个字母出现的次数
// 变量count记录t的字母中与s不同的字母个数
// 初始时count为s的长度，遍历t，若t的当前字母是s中的字母，就把count--
// 最终若count == 0，则是anagram

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s.length() == 0 || t.length() == 0) {
            return true;
        }
        int[] countChar = new int[26];
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            countChar[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--countChar[t.charAt(i) - 'a'] >= 0) {
                count--;
            }
        }
        return count == 0;
    }
}