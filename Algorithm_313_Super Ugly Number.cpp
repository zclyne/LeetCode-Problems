#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    int nthSuperUglyNumber(int n, vector<int>& primes) {
        if (n == 1) return 1;
        vector<int> results(n + 1, 1);
        vector<int> pointers(primes.size(), 1);
        for (int i = 2; i <= n; i++)
        {
            results[i] = getMin(primes, pointers, results);
            for (int j = 0; j < pointers.size(); j++) if (primes[j] * results[pointers[j]] == results[i]) pointers[j]++;
        }
        return results[n];
    }
    int getMin(vector<int> &primes, vector<int> &pointers, vector<int> &results)
    {
        int minNum = INT_MAX;
        for (int i = 0; i < primes.size(); i++) if (primes[i] * results[pointers[i]] < minNum) minNum = primes[i] * results[pointers[i]];
        return minNum;
    }
};