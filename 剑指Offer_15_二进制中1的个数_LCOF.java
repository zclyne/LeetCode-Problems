// 本题与Algorithm_191是同一题
// 思路：无符号右移
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n & 1;
            n = n >>> 1; // 无符号右移，空位用0补齐
        }
        return cnt;
    }
}