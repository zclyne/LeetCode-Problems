// 思路：本质上是26进制

class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res *= 26;
            res += s.charAt(i) - 'A' + 1;
        }
        return res;
    }
}