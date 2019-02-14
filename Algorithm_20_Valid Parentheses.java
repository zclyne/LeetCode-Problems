// 思路：用栈来匹配符号。若当前输入符号是左括号，则入栈。若是右括号，判断栈顶符号是否为对应左括号
// 若是，则匹配成功，弹栈；若不是，则匹配失败，直接返回false
// 注意判断栈顶元素之前要先判断栈是否为空
// Stack是基于Vector的类，性能不佳，用LinkedList更好

import java.util.Stack;
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '{' || curChar == '[' || curChar == '(') {
                stack.push(curChar);
            } else if (curChar == '}') {
                if (stack.isEmpty() || stack.peek() != '{') { // remember to check if stack is empty
                    return false;
                } else {
                    stack.pop();
                }
            } else if (curChar == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } 
        }
        return stack.isEmpty();
    }
}