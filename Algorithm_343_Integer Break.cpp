#include <iostream>
using namespace std;
class Solution {
public:
    int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        int res = 1;
        while (n > 4)
        {
            n -= 3;
            res *= 3;
        }
        res *= n;
        return res;
    }
};

// another java dp solution, not better, but easy to understand
// public int integerBreak(int n) {
//     int[] dp = new int[n + 1];
//     dp[1] = 1;
//     for(int i = 2; i <= n; i ++) {
//         for(int j = 1; j < i; j ++) {
//             dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
//         }
//     }
//     return dp[n];
// }