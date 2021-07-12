// 方法：直接做

class Solution {
    public int addDigits(int num) {
        while (num / 10 > 0) {
            int tmp = num, newNum = 0;
            while (tmp > 0) {
                newNum += tmp % 10;
                tmp /= 10;
            }
            num = newNum;
        }
        return num;
    }
}