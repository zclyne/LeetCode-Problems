import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// 方法：首先对s的第一个字符进行判断。如果不是'['，说明s只表示了一个integer，直接遍历一遍，parse出这个int并返回即可
// 否则，创建一个栈stack和一个当前的NestedInteger，记为cur并把cur压入栈顶。然后开始遍历s
// 1. 若当前字符为'-'或数字，则表示一个integer的开始，调用parseInt方法来得到这个int以及下标i所应该跳转到的下一个位置
// 将这个int add到当前的NestedInteger cur中，然后更新i
// 2. 若当前字符为左括号'['，代表是一个新的list的NestedInteger的开始。创建一个新的NestedInteger，记为newCur，将其压入stack，并令cur = newCur
// 3. 若当前字符为右括号']'，表明一个list类型的NestedInteger的结束。这里要对i进行讨论
// 3.1. 如果i == s.length() - 1，则当前的cur是最外层的NestedInteger，不做任何操作，直接i++
// 3.2. 如果i < s.length() - 1，则当前的cur外层还有其他的NestedInteger，从栈中pop出栈顶元素，将新的栈顶元素stack.peek()赋予cur
// 4. 若当前字符为逗号','，直接i++
// 最终，stack中只剩下一个元素，就是最外层的NestedInteger，将其作为返回值返回

class Solution {
    public NestedInteger deserialize(String s) {
        char[] chars = s.toCharArray();

        // s represents only a single integer
        if (chars[0] != '[') {
            return new NestedInteger(parseInt(chars, 0)[0]);
        }
        
        // chars[0] == '['
        int i = 1;
        Stack<NestedInteger> stack = new Stack<>();

        NestedInteger cur = new NestedInteger();
        stack.push(cur);
        while (i < chars.length) {
            if (chars[i] == '-' || Character.isDigit(chars[i])) {
                int[] parseResult = parseInt(chars, i);
                int num = parseResult[0], nextI = parseResult[1];
                cur.add(new NestedInteger(num));
                i = nextI;
            } else if (chars[i] == '[') {
                NestedInteger newCur = new NestedInteger();
                cur.add(newCur);
                stack.push(newCur);
                cur = newCur;
                i++;
            } else if (chars[i] == ']') {
                if (i < chars.length - 1) { // not the last char
                    stack.pop();
                    cur = stack.peek();
                }
                i++;
            } else if (chars[i] == ',') {
                i++;
            }
        }

        return stack.pop();
    }

    // returns the parsed integer number and the index after the last digit of the integer
    private int[] parseInt(char[] chars, int start) {
        int result = 0;
        int end = start;
        if (chars[start] == '-') {
            end++;
        }
        while (end < chars.length && Character.isDigit(chars[end])) {
            result = result * 10 + (chars[end] - '0');
            end++;
        }
        
        if (chars[start] == '-') {
            result = -result;
        }

        return new int[]{result, end};
    }
}