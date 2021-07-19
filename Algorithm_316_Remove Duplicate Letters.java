import java.util.Stack;

// 方法：单调栈
// 用数组letterCount记录s中每个字母出现的次数，栈stack存储字母
// 遍历s，先将s[i]的count--。如果s[i]已经被添加到了栈中（used[s[i]] == true)，直接continue进入下一轮循环
// 否则，不断检查stack栈顶元素，如果栈顶元素c比s[i]大，并且letterCount[c - 'a'] > 0，说明在当前字母s[i]的后面
// 仍然有c，可以把栈顶的c删除，stack.pop()，直到栈顶元素<=s[i]或栈顶元素的count == 0为止
// 然后将s[i]加入栈中，used[s[i] - 'a'] = true
// https://leetcode-cn.com/problems/remove-duplicate-letters/solution/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/

class Solution {
    public String removeDuplicateLetters(String s) {
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