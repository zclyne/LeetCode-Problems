// 思路：直接右移32次，并和1做与操作，判断最低为是否为1

class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n >>= 1;
        }
        return res;
    }
}

// n & (n - 1) Solution
// 每次把n和n - 1做与操作时，就会把n中最右边的1变成0 

class AnotherSolution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }
}

// BitCountSolution
// 直接用Integer类中的静态方法

class BitCountSolution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}