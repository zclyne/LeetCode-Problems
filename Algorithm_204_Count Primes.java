// 思路：notPrime[i]存储数字i是否为prime，为true时表示不是prime
// 对任意i >= 2，先判断notPrime[i] 是否为true。若已经为true，则不仅i不是prime，所有i的倍数也已经被访问过，直接continue
// 若i本身是prime，由于所有i的倍数都不是prime，置notPrime[i * j] = true

class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i]) continue;
            res++;
            for (int j = 2; i * j < n; j++) {
                notPrime[i * j] = true;
            }
        }
        return res;
    }
}