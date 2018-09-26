#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    vector<int> singleNumber(vector<int> &nums)
    {
        vector<int> res;
        int save = 0;
        for (int i = 0; i < nums.size(); i++) save ^= nums[i];
        save = (save & (save - 1)) ^ save;
        int a = 0, b = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] & save) a ^= nums[i];
            else b ^= nums[i];
        }
        return vector<int> {a, b};
    }
};