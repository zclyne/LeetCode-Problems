import java.util.*;

// 方法：用栈存储字符和字符对应的计数
// 遍历s，如果当前字符和栈顶字符相同，则将栈顶字符count++
// 如果栈顶字符的count == k，则把栈顶元素弹栈，表示这k个相同字符被消去
// 如果栈为空或当前字符和栈顶字符不同，则将当前字符压栈，count为1
// 遍历完成后，从栈底到栈顶依次添加剩余字符，最终结果就是答案

class Solution {

    class Node {
        public char c;
        public int count;
        public Node(char c) {
            this.c = c;
            this.count = 1;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<Node> deque = new LinkedList<>();
        deque.push(new Node(s.charAt(0)));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!deque.isEmpty() && deque.peek().c == c) {
                deque.peek().count++;
                if (deque.peek().count == k) {
                    deque.pop();
                }
            } else {
                deque.push(new Node(c));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!deque.isEmpty()) {
            Node node = deque.pollLast();
            while (node.count > 0) {
                stringBuilder.append(node.c);
                node.count--;
            }
        }
        return stringBuilder.toString();
    }
}