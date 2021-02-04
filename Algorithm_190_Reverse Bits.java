// 思路：直接循环32次，进行位运算
// 注意循环体内，result左移操作应在或操作之前进行
// 如果顺序颠倒，则在最后一次或操作结束后，result再次发生左移，结果就不正确了

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result |= n & 1;
            n = n >> 1;
        }
        return result;
    }
}