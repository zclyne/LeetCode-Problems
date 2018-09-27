#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;
class Solution
{
  public:
    int numSquares(int n)
    {
        double sqr = sqrt(n);
        if (sqr == int(sqr)) return 1; // n itself is a perfect square
        vector<int> result(n + 1, 0);
        for (int i = 1; i <= n; i++)
        {
            sqr = sqrt(i);
            if (sqr == int(sqr)) result[i] = 1;
            else
            {
                int tmpMin = INT_MAX;
                for (int j = 1; j < sqr; j++) tmpMin = min(tmpMin, result[i - j * j] + 1);
                result[i] = tmpMin;
            }
        }
        return result[n];
    }
};
