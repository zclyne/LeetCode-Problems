// 方法：将n不断右移2位（相当于除以4），如果n是4的幂，那么在右移过程中，
// n的最低两位应该始终都是0，这样才能保证被4整除，即n & 3 == 0
// 最后若n == 1，说明是4的幂，反之则不是

class Solution {
    public boolean isPowerOfFour(int n) {
        while (n > 1 && ((n & 3) == 0)) {
            n >>= 2;
        }
        return n == 1;
    }
}