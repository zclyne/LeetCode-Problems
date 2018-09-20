#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
  public:
    int maxProduct(vector<int> &nums)
    {
        int maxNum = INT_MIN;
        vector<int> maxVec(nums.size()), minVec(nums.size());
        maxVec[0] = minVec[0] = nums[0];
        for (int i = 1; i < nums.size(); i++)
        {
            maxVec[i] = max(max(nums[i], nums[i] * maxVec[i - 1]), nums[i] * minVec[i - 1]);
            minVec[i] = min(min(nums[i], nums[i] * maxVec[i - 1]), nums[i] * minVec[i - 1]);
        }
        for (int i = 0; i < nums.size(); i++) if (maxVec[i] > maxNum) maxNum = maxVec[i];
        return maxNum;
    }
};