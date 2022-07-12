// 双指针直接遍历，每遇到一个空格，就翻转前面的单词

class Solution {
    public String reverseWords(String s) {
        int start = 0, end = 0;
        StringBuilder sb = new StringBuilder();
        while (start < s.length()) {
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            String word = s.substring(start, end);
            sb.append(new StringBuilder(word).reverse().toString());
            if (end < s.length()) {
                sb.append(' ');
            }
            end++; // skip a space
            start = end;
        }
        return sb.toString();
    }
}