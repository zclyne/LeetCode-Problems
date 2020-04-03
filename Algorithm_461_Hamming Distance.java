// My Solution
// 思路：循环32次，每次将x和y的最低位通过和1做与运算的方式提取出来，并做异或运算

class Solution {
    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += 1 & (x ^ y);
            x >>= 1;
            y >>= 1;
        }
        return count;
    }
}

// Tricky Solution
class TrickySolution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}