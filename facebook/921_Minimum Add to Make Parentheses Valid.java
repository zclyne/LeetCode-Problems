// 方法：result记录结果，leftParenCount记录当前遇到的左括号数量
// 遍历s，如果当前字符是左括号，则leftParenCount++
// 如果当前字符是右括号，则
// 1. 如果leftParenCount > 0，说明可以把当前右括号和之前遇到过的一个左括号配对，消耗掉一个左括号，leftParenCount--
// 2. 如果leftParenCount == 0，说明需要在当前右括号左边加上一个左括号才能使得整个字符串是valid的，result++
// 最后，leftParenCount记录了还没有配对的左括号的个数，需要加上同等数量的右括号才能配对
// 所以最终答案是result + leftParenCount

class Solution {
    public int minAddToMakeValid(String s) {
        int result = 0;
        int leftParenCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftParenCount++;
            } else if (s.charAt(i) == ')') {
                if (leftParenCount > 0) {
                    leftParenCount--;
                } else {
                    result++;
                }
            }
        }
        return result + leftParenCount;
    }
}