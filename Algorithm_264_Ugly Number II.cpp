#include <iostream>
#include <unordered_set>
#include <algorithm>
using namespace std;
class Solution
{
  public:
    int nthUglyNumber(int n)
    {
        if (n <= 5) return n;
        vector<int> nums(n, 1);
        int pointer_2 = 0, pointer_3 = 0, pointer_5 = 0;
        for (int i = 0; i < n; i++)
        {
            nums[i] = min(nums[pointer_2] * 2, min(nums[pointer_3] * 3, nums[pointer_5] * 5));
            if (nums[i] == nums[pointer_2] * 2) pointer_2++;
            if (nums[i] == nums[pointer_3] * 3) pointer_3++;
            if (nums[i] == nums[pointer_5] * 5) pointer_5++;
        }
        return nums[n - 1];
    }
};