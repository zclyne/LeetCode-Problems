// 方法：要求数字的digit必须unique，就相当于从0~9这10个数字中选取若干位进行排列，也就是排列数问题
// 例如要组成一个5位数，则就相当于10P5，从0~9这10个数字中任选5个
// 但要注意的是，0不能作为数字的首位，所以必须把以0开头的所有数字都删除，除去首位的0，还剩下4位
// 并且可供选择的数字只有1~9这9个，所以就是9P4
// 所以，5位数中，digit各不相同的数字总数就是10P5 - 9P4
// 对于任意10^n，要考虑的是从1到n位数
// 用temp数组记录下各个位数对应的digit各不相同的数字总数
// 最后把temp中的值从下标0加到n，就是答案

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int[] temp = new int[9];
        temp[0] = 1;
        for (int i = 1; i <= 8; i++) {
            // calculate 10Pi
            int p1 = 1;
            for (int j = 11 - i; j <= 10; j++) {
                p1 *= j;
            }
            // calculate 9P(i - 1)
            int p2 = 1;
            for (int j = 11 - i; j <= 9; j++) {
                p2 *= j;
            }
            temp[i] = p1 - p2;
        }

        int result = 0;
        for (int i = 0; i <= n; i++) {
            result += temp[i];
        }
        return result;
    }
}