// 方法：直接遍历s，记录遍历过程中左括号数量的最大值
// 遇到左括号时countLeftParen++，遇到右括号时countLeftParen--

class Solution {
    public int maxDepth(String s) {
        int result = 0;
        int countLeftParen = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                countLeftParen++;
            } else if (c == ')') {
                countLeftParen--;
            }
            result = Math.max(result, countLeftParen);
        }
        return result;
    }
}