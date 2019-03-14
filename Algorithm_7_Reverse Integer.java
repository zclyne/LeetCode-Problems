// 思路：循环把x对10取模的结果加到res上。通过判断(res - x % 10) / 10 != lastRes是否成立来知道是否发生溢出
// 不需要考虑x的政府，因为while中的条件用的是x != 0而不是x > 0

class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int lastRes = res;
            res = res * 10 + x % 10;
            if ((res - x % 10) / 10 != lastRes) { // an overflow happens
                return 0;
            }
            x /= 10;
        }
        return res;
    }
}