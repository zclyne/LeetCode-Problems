// 思路：t中新加的字符只出现一次，而其他字符都出现偶数次，因此把所有字符做异或后
// 最终结果就是要求的结果
// 字符的异或本质上是先转换成int，再异或，再转换回char

class Solution {
    public char findTheDifference(String s, String t) {
        char c = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
}