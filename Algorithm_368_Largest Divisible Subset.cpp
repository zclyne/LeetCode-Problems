#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
private:
public:
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        if (!nums.size() || nums.size() == 1) return nums;
        sort(nums.begin(), nums.end());
        vector<int> nextIndex(nums.size(), -1);
        vector<int> maxLen(nums.size(), 1);
        vector<int> res;
        int curMaxLen = 1, startIndex = 0;
        for (int i = nums.size() - 1; i >= 0; i--)
        {
            for (int j = nums.size() - 1; j > i; j--)
            {
                if (nums[j] % nums[i] != 0) continue;
                if (maxLen[j] + 1 > maxLen[i])
                {
                    maxLen[i] = maxLen[j] + 1;
                    nextIndex[i] = j;
                }
            }
            if (maxLen[i] > curMaxLen)
            {
                curMaxLen = maxLen[i];
                startIndex = i;
            }
        }
        while (startIndex != -1)
        {
            res.push_back(nums[startIndex]);
            startIndex = nextIndex[startIndex];
        }
        return res;
    }
};