import java.util.Deque;
import java.util.LinkedList;

// 方法：单调栈
// 顺序遍历num。对于当前位，如果它小于栈顶位，并且k > 0
// 则表明可以消耗掉一个k，来删除掉栈顶元素
// 不断比较栈顶元素和当前位的关系，直到栈为空/栈顶元素不大于当前位/k == 0时
// 将当前位cur添加到栈顶
// 这样就能够保证在k用完以前，栈中元素从栈底到栈顶是单调不减的
// 如果遍历完成后，k仍然大于0，则说明整个栈中元素都单调不减
// 因此需要从栈顶删除掉k个元素，来使得最终得到的结果最小
// 还要注意栈中可能存在前导0，需要单独处理
// 最终如果结果字符串的长度为0，说明答案为0；否则返回结果
// https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/

class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        deque.offerLast(num.charAt(0));

        for (int i = 1; i < num.length(); i++) {
            char cur = num.charAt(i);
            while (!deque.isEmpty() && deque.peekLast() > cur && k > 0) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(cur);
        }

        // if k is not used up
        while (k > 0) {
            deque.pollLast();
            k--;
        }

        // handle leading 0s
        StringBuilder stringBuilder = new StringBuilder();
        while (!deque.isEmpty() && deque.peekFirst() == '0') {
            deque.pollFirst();
        }

        while (!deque.isEmpty()) {
            stringBuilder.append(deque.pollFirst());
        }

        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }
}