import java.util.Deque;
import java.util.LinkedList;

// monotonic stack

class Solution {
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            count[curChar - 'a']--;
            if (used[curChar - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > curChar) {
                char topChar = stack.peek();
                if (count[topChar - 'a'] > 0) { // still have topChar after curChar 
                    stack.pop();
                    used[topChar - 'a'] = false;
                } else {
                    break;
                }
            }
            stack.push(curChar);
            used[curChar - 'a'] = true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        return stringBuilder.toString();
    }
}