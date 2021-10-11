import java.util.*;

// 方法：遍历s，并用栈存储字符
// 如果当前字符和栈顶字符相同，则从栈顶pop字符
// 否则，将当前字符加入栈顶
// 最后从栈底到栈顶的字符组成的就是结果

class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!deque.isEmpty() && deque.peek() == s.charAt(i)) {
                deque.pop();
            } else {
                deque.push(s.charAt(i));
            }
        }
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append(deque.pollLast());
        }
        return result.toString();
    }
}