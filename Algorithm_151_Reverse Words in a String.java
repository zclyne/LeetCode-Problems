// 思路：反向遍历s，用栈存储单词，从而保证单词内部的字母顺序不变
// 每当遇到一个空格时，就判断word是否为空，若不为空，则把word中的内容添加到StringBuilder上

class Solution {
    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        Stack<Character> word = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (!word.empty()) {
                    this.putWord(builder, word);
                }
            } else {
                word.push(s.charAt(i));
            }
        }
        if (!word.empty()) {
            putWord(builder, word);
        }
        return builder.toString();
    }

    private void putWord(StringBuilder builder, Stack<Character> word) {
        if (builder.length() != 0) { // add a space if word is not the first word
            builder.append(' ');
        }
        while (!word.empty()) {
            builder.append(word.pop());
        }
        return;
    }
}