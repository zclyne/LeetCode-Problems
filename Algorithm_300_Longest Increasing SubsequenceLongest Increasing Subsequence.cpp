#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution
{
  public:
    int lengthOfLIS(vector<int> &nums)
    {
        if (nums.size() == 0) return 0;
        vector<int> dpSave(nums.size(), 1);
        int maxLen = 1;
        for (int i = 1; i < nums.size(); i++)
        {
            int curMax = 1;
            for (int j = 0; j < i; j++) if (nums[i] > nums[j]) curMax = max(curMax, dpSave[j] + 1);
            dpSave[i] = curMax;
            maxLen = max(maxLen, curMax);
        }
        return maxLen;
    }
};

// better O(nlogn) solution
class Solution
{
  public:
    int lengthOfLIS(vector<int> &nums)
    {
        if (nums.size() == 0 || nums.size() == 1) return nums.size();
        vector<int> tails(nums.size(), nums[0]);
        int curSize = 1;
        for (int i = 1; i < nums.size(); i++)
        {
            if (nums[i] > tails[curSize - 1]) tails[curSize++] = nums[i];
            else
            {
                int tmp = findPos(tails, curSize, nums[i]);
                tails[tmp] = nums[i];
            }
        }
        return curSize;
    }
    int findPos(vector<int> tails, int curSize, int n)
    {
        int low = 0, high = curSize;
        while (low < high)
        {
            int mid = (low + high) / 2;
            if (tails[mid] < n) low = mid + 1;
            else high = mid;
        }
        return high;
    }
};