// DP Solution, beats 90.66%
// 思路：longest[i]存放的是以s[i]结尾的最长匹配的长度
// 若s[i] == '('，置longest[i] = 0，因为任何匹配的最后一个字符一定不可能是'('
// 若s[i] == ')'，有两种情况：
// 1. 若s[i - 1] == '('，则longest[i] = longest[i - 2] + 2
// 2. 若s[i - 1] == ')'且s[i - longest[i - 1] - 1] == '('，longest[i] = longest[i - 1] + 2 + longest[i - longest[i - 1] - 2]

class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxLen = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        dp[i] += i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0;
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}


// Stack Solution，beats 37.7%
// 思路：用stack存放不匹配的括号在s中的下标
// 若当前读到的是左括号，直接把对应下标入栈
// 若当前读到的是右括号，检查栈顶是否为左括号。若是，则匹配，将栈顶元素弹栈；否则把当前下标入栈
// 对s遍历完成后，栈中存放的就是无法匹配的符号的下标，则相邻两栈中元素之间的字符串是匹配的
// 找栈中相邻两元素之差的最大值 - 1，即为最长的匹配substring的长度

// import java.util.Stack;
// class Solution {
//     public int longestValidParentheses(String s) {
//         Stack<Integer> stack = new Stack<>();
//         for (int i = 0; i < s.length(); i++) {
//             if (s.charAt(i) == '(') {
//                 stack.push(i);
//             } else {
//                 if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') { // match
//                     stack.pop();
//                 } else {
//                     stack.push(i);
//                 }
//             }
//         }
//         int maxLen = 0;
//         if (stack.isEmpty()) {
//             maxLen = s.length();
//         } else {
//             int end = s.length();
//             while (!stack.isEmpty()) {
//                 int start = stack.pop();
//                 maxLen = Math.max(maxLen, end - start - 1);
//                 end = start;
//             }
//             maxLen = Math.max(maxLen, end); // when start == 0
//         }
//         return maxLen;
//     }
// }