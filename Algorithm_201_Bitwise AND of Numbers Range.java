// m和n一定具有一段公共的前缀，在公共前缀之后的数字全部为0
// 因为m和n之间的数字必然覆盖了公共前缀之后的部分的所有情况
// 按位与后，都是0
// https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/solution/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }
}

// 方法2：Brian Kernighan算法
// 每次计算n & n - 1后，n中最右边的1会被抹去，变成0
// 对n迭代进行这一操作，直到n <= m，此时非公共前缀部分的1全部被消去

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }
}