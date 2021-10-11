import java.util.*;

// stack

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