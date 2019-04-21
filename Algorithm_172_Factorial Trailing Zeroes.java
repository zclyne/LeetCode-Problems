// 思路：trailing zero的个数取决于2和5的个数，且2的数量远大于5的数量，因此trailing zero的个数等于factorial中5的个数

class Solution {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}

// 思路：https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52373/Simple-CC%2B%2B-Solution-(with-detailed-explaination)

class IterativeSolution {
    public int trailingZeroes(int n) {
        int res = 0;
        for (long i = 5; n / i > 0; i *= 5) {
            res += n / i;
        }
        return res;
    }
}