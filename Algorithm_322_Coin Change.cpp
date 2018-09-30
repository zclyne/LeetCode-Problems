#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

//recursive DP solution. Time Limit Exceeded
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        if (!amount) return 0;
        int minNum = INT_MAX;
        for (int i = 0; i < coins.size(); i++)
        {
            if (coins[i] <= amount)
            {
                int curNum = 0;
                int tmp = coinChange(coins, amount - coins[i]);
                if (tmp != -1) curNum = 1 + tmp;
                if (curNum) minNum = min(curNum, minNum);
            }
        }
        return minNum == INT_MAX ? -1 : minNum;
    }
};

// better iterative DP solution
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1, INT_MAX);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.size(); j++) {
                if (coins[j] <= i) {
                    dp[i] = min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == INT_MAX ? -1 : dp[amount];
    }
};