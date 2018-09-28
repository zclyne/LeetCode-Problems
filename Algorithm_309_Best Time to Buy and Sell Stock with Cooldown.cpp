#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (!prices.size() || prices.size() == 1) return 0;
        vector<int> hasStock(prices.size()), coolDown(prices.size()), notHaveStock(prices.size());
        hasStock[0] = -prices[0];
        coolDown[0] = INT_MIN;
        notHaveStock[0] = 0;
        for (int i = 1; i < prices.size(); i++)
        {
            hasStock[i] = max(hasStock[i - 1], notHaveStock[i - 1] - prices[i]);
            coolDown[i] = hasStock[i - 1] + prices[i];
            notHaveStock[i] = max(notHaveStock[i - 1], coolDown[i - 1]);
        }
        return max(coolDown[prices.size() - 1], notHaveStock[prices.size() - 1]);
    }
};