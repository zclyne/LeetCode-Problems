#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    vector<int> countBits(int num) {
        vector<int> res(num + 1, 0);
        res[1] = 1;
        int latestPowIndex = 1;
        for (int i = 2; i <= num; i++)
        {
            if (i == latestPowIndex * 2)
            {
                latestPowIndex *= 2;
                res[i] = 1;
            }
            else res[i] = 1 + res[i - latestPowIndex];
        }
        return res;
    }
};