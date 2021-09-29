import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// 方法：用栈存储左括号(的下标
// 先遍历一边s，如果当前字符是左括号，则压入栈中
// 如果当前字符是右括号，则判断栈是否为空
// 若栈不为空，则从栈中pop一次，表示一个左括号可以由当前右括号配对
// 若栈为空，则需要把当前右括号移除，将当前下标i加入toRemove set中
// 遍历完成后，如果栈中仍有数据，则说明是无法配对的左括号，也要删除
// 不断从栈中pop，并将pop出的元素放入toRemove中，直到最终栈为空
// 再遍历一边s，若当前字符的下标在toRemove中，则说明是要删除的字符，不做处理
// 否则，将当前字符加入stringBuilder
// 最终stringBuilder.toString()即为答案

class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new LinkedList<>();
        Set<Integer> toRemove = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) { // need to remove the )
                    toRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        // the remaining ( in the stack also needs to be removed
        while (!stack.isEmpty()) {
            toRemove.add(stack.pop());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                stringBuilder.append(s.charAt(i));
            }
            
        }
        return stringBuilder.toString();
    }
}