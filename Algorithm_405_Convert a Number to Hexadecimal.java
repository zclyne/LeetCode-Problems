import java.util.Deque;
import java.util.LinkedList;

// 方法：位运算
// 每次取num的二进制表示的最低四位，将其转换成十六进制数字，加入栈中
// 然后将num无符号右移4位，直到最后num为0
// 最后从栈中先pop出所有的leading 0，再讲剩余的字符都添加到result中并返回

class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        Deque<Character> deque = new LinkedList<>();

        while (num != 0) {
            int hexDigits = num & 15;
            char hexDigit = getHexDigit(hexDigits);
            deque.offerLast(hexDigit);
            num >>>= 4;
        }

        while (!deque.isEmpty() && deque.peekLast() == '0') {
            deque.pollLast();
        }

        String result = "";
        while (!deque.isEmpty()) {
            result = result + deque.pollLast();
        }

        return result;
    }

    private char getHexDigit(int num) {
        if (num < 10) {
            return (char) ('0' + num);
        } else {
            return (char) ('a' + num - 10);
        }
    }
}