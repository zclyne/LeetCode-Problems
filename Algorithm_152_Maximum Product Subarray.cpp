#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
  public:
    int maxProduct(vector<int> &nums)
    {
        int maxNum = nums[0], lastMax = nums[0], lastMin = nums[0];
        for (int i = 1; i < nums.size(); i++)
        {
            int tmpMax = max(max(nums[i], nums[i] * lastMax), nums[i] * lastMin);
            lastMin = min(min(nums[i], nums[i] * lastMax), nums[i] * lastMin);
            lastMax = tmpMax;
            if (lastMax > maxNum) maxNum = lastMax;
        }
        return maxNum;
    }
};