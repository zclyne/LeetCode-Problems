// 思路：https://leetcode-cn.com/problems/nim-game/solution/nimyou-xi-by-leetcode/

class Solution {
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}