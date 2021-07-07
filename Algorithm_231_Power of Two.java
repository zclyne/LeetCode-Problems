// 方法1：直接用Integer.bitCount得到n的二进制表达下的1的个数
// 注意n为负数的特殊情况

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return Integer.bitCount(n) == 1;
    }
}

// 方法2：利用n & (n - 1)能够消去末尾的1的性质

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}