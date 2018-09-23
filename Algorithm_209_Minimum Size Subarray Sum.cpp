#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    int minSubArrayLen(int s, vector<int>& nums) {
        int curSum = 0, start = 0, end;
        for (end = 0; end < nums.size(); end++)
        {
            curSum += nums[end];
            if (curSum >= s) break;
        }
        if (end == nums.size()) return 0;
        if (end == nums.size() - 1) return end;
        if (end == 0) return 1;
        int minLen = end - start + 1;
        while (start < nums.size())
        {
            start++;
            if (end < nums.size() - 1)
            {
                end++;
                curSum = curSum - nums[start - 1] + nums[end];
            }
            else curSum -= nums[start - 1]; // has already reached the end
            if (curSum < s) // curSum is not available
            {
                if (end < nums.size() - 1) continue; // hasn't reach the end of nums, start with the next character and maintain the current length
                else return minLen; // has already reached the end of nums, no need to compute further
            }
            else // curSum is available, try reducing the length
            {
                while (end >= start && curSum - nums[end] >= s) // deduct the last num from curSum, and compare the curSum with s until newSum < s
                {
                    curSum -= nums[end];
                    end--;
                }
                // can't reduce the length anymore, update minLen and switch to the next start character
                end++;
                curSum += nums[end];
                minLen = min(minLen, end - start + 1);
            }
        }
        return minLen;
    }
};