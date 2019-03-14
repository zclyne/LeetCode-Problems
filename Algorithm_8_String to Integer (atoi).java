// 思路：首先判断str是否为null以及把str的首尾空格都取出后str的长度是否为0，若满足则直接返回0
// 去除空格后，判断str的第一个字符是不是符号，若是，判断是+还是-，用变量negative存储符号信息，startIdx还要置为1，表示跳过这个符号开始找数字
// 循环找数字。若当前字符不是数字，则直接退出循环
// 若是数字，要判断当前res*10并加上这个数字之后是否会溢出，必须通过Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < curChar - '0')来判断
// java中的溢出：2147483647 + 1 = -2147483648。如此循环
// 若产生溢出，则根据negative的值来决定返回Integer.MIN_VALUE还是Integer.MAX_VALUE

class Solution {
    public int myAtoi(String str) {
        int res = 0;
        if (str == null || (str = str.trim()).length() == 0) { // str.trim(): get rid of the white prepending and appending spaces
            return res;
        }
        boolean negative;
        int startIdx;
        if (str.charAt(0) == '-') {
            negative = true;
            startIdx = 1;
        } else {
            negative = false;
            startIdx = str.charAt(0) == '+' ? 1 : 0;
        }
        for (int i = startIdx; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (!(curChar >= '0' && curChar <= '9')) { // encounter invalid char, quit the loop
                break;
            }
            if (Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < curChar - '0')) { // overflow
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + curChar - '0';
        }
        return negative ? -res : res;
    }
}