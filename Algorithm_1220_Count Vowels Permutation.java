// 方法：有限状态机，画出状态转移图
// https://leetcode.com/problems/count-vowels-permutation/discuss/398222/Detailed-Explanation-using-Graphs-With-Pictures-O(n)
// 注意要用long类型，int会溢出

class Solution {
    public int countVowelPermutation(int n) {
        final int MOD = 1000000007;
        long countA = 1, countE = 1, countI = 1, countO = 1, countU = 1;
        for (int i = 1; i < n; i++) {
            long newCountA = (countE + countI + countU) % MOD;
            long newCountE = (countA + countI) % MOD;
            long newCountI = (countE + countO) % MOD;
            long newCountO = countI % MOD;
            long newCountU = (countI + countO) % MOD;
            countA = newCountA;
            countE = newCountE;
            countI = newCountI;
            countO = newCountO;
            countU = newCountU;
        }
        long result = 0;
        result = (result + countA) % MOD;
        result = (result + countE) % MOD;
        result = (result + countI) % MOD;
        result = (result + countO) % MOD;
        result = (result + countU) % MOD;
        return (int) result;
    }
}