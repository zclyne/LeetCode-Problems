#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution
{
  public:
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        if (n < 2)  return n ? nums[0] : 0;
        if (n == 2) return max(nums[0], nums[1]);
        return max(robber(nums, 0, n - 2), robber(nums, 1, n - 1));
    }

  private:
    int robber(vector<int> nums, int l, int r)
    {
        if (r - l == 2) return max(nums[l] + nums[r], nums[l + 1]);
        nums[l + 2] = nums[l + 2] + nums[l];
        for (int i = l + 3; i <= r; i++) nums[i] = max(nums[i - 3] + nums[i], nums[i - 2] + nums[i]);
        return max(nums[r], nums[r - 1]);
    }
};