import java.util.Stack;

// My Solution
// 思路：用栈存储计算结果，每次读入符号时，弹出栈顶的2个元素做相应运算后再放回栈中
// 最终栈顶元素就是计算结果

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        for (String cur : tokens) {
            if (cur.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (cur.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (cur.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (cur.equals("/")) {
                int divisor = stack.pop(), dividend = stack.pop();
                stack.push(dividend / divisor);
            } else {
                stack.push(Integer.parseInt(cur));
            }
        }
        return stack.pop();
    }
}