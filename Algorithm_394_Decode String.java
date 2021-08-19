import java.util.Stack;

// 方法1：递归
// while只处理一对[]之间的内容，假设该[]之间的字符串已经decode完成，记为tmp，res就是以这对[]之前的数字作为次数，把tmp重复这么多次得到的字符串
// 若这对[]内部还有嵌套的[]，递归调用decodeString(..)

class Solution {
    private int i = 0;
    public String decodeString(String s) {
        String res = "";
        while (i < s.length() && s.charAt(i) != ']') {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count *= 10;
                    count += s.charAt(i++) - '0';
                }
                i++; // handle '['
                String tmp = decodeString(s);
                i++; // handle ']'
                while (count-- > 0) {
                    res += tmp;
                }
            } else {
                res += s.charAt(i++);
            }
        }
        return res;
    }
}

// 方法2：迭代
// 遍历s，用两个栈countStack和stringStack分别存储遍历过程中遇到的要重复的次数，以及要重复的字符串本身
// 用变量count和cur分别记录当前的要重复的次数和要重复的字符串
// 若当前字符是一个数字，则相应地加到count上
// 若当前字符是一个字母，则加到cur的尾端
// 若当前字母是左括号'['，则说明即将进入下一层，把当前的count和cur压栈，然后分别清空
// 若当前字母是右括号']'，说明要从这一层退出，从countStack中弹出要重复的次数replicate
// 然后将当前的字符串cur重复replicate次，再从栈中弹出上一层的字符串newCur，把当前的重复了replicate次的cur的结果添加到newCur末尾
// 最后令cur = newCur

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        char[] chars = s.toCharArray();

        int count = 0;
        int i = 0;
        String cur = "";
        while (i < chars.length) {
            if (Character.isDigit(chars[i])) {
                count = count * 10 + (chars[i] - '0');
            } else if (Character.isLetter(chars[i])) {
                cur += chars[i];
            } else if (chars[i] == '[') {
                countStack.push(count);
                stringStack.push(cur);
                cur = "";
                count = 0;
            } else if (chars[i] == ']') {
                // repeat cur for replicate times
                int replicate = countStack.pop();
                StringBuilder stringBuilder = new StringBuilder();
                while (replicate > 0) {
                    stringBuilder.append(cur);
                    replicate--;
                }
                String newCur = stringStack.pop();
                newCur += stringBuilder.toString();
                cur = newCur;
            }
            i++;
        }

        return cur;
    }
}