// 思路：对某一个数i，若它是偶数，则其二进制种1的个数与i / 2相同，即res[i] = res[i >> 1]
// 若它是奇数，则要加上二进制中最低位的1，则res[i] = res[i >> 1] + (i & 1)
// 注意不要省略i & 1两边的括号

class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}