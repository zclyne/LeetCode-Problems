// 思路：直接遍历数组bits，若当前位为1，则跳过下一个bit
// 注意循环的终止条件。若最终i == len - 1，说明第len - 2位被跳过，答案为true
// 否则，跟组bits[len - 2]的值来判断答案为true还是false

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length, i = 0;
        for (; i < len - 2; i++) if (bits[i] == 1) i += 1;
        if (i == len - 1) return true;
        return bits[i] == 0;
    }
}