#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    vector<vector<int> > combinationSum3(int k, int n)
    {
        return getCom(k, n, 0);
    }
    vector<vector<int> > getCom(int k, int n, int lastVal)
    {
        vector<vector<int> > res;
        if (k == 0 || k == 1 && n > 9 || lastVal > 9) return res;
        if (k == 1 && n > lastVal && n <= 9)
        {
            vector<int> tmpRes(1, n);
            res.push_back(tmpRes);
            return res;
        }
        for (int i = lastVal + 1; i <= 9; i++)
        {
            vector<vector<int> > subRes = getCom(k - 1, n - i, i);
            for (int j = 0; j < subRes.size(); j++)
            {
                subRes[j].push_back(i);
                res.push_back(subRes[j]);
            }
        }
        return res;
    }
};