// 方法：与Algorithm_316是同一题，见316的笔记

class Solution {
    public String smallestSubsequence(String s) {
        char[] sc = s.toCharArray();
        int[] letterCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char letter = sc[i];
            letterCount[letter - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            letterCount[sc[i] - 'a']--;
            if (used[sc[i] - 'a']) { // sc[i] has already been added to the stack
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > sc[i]) {
                char c = stack.peek();
                if (letterCount[c - 'a'] == 0) { // cannot remove c, because there is no c after sc[i]
                    break;
                }
                stack.pop();
                used[c - 'a'] = false;
            }
            stack.push(sc[i]);
            used[sc[i] - 'a'] = true;
        }

        // build the result
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        
        return stringBuilder.reverse().toString();
    }
}