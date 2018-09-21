#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    int findMin(vector<int> &nums)
    {
        int start = 0, end = nums.size() - 1, mid;
        while (start < end)
        {
            mid = (start + end) / 2;
            if (nums[start] < nums[end]) return nums[start];
            if (nums[start] < nums[mid] || mid == start) start = mid + 1;
            else if (nums[start] > nums[mid]) end = mid;
        }
        return nums[start];
    }
};