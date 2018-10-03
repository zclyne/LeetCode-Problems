#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        if (nums.size() < 3) return false;
        if (nums.size() == 3) return nums[0] < nums[1] && nums[1] < nums[2];
        vector<int> mins(nums.size());
        mins[0] = nums[0];
        for (int i = 1; i < nums.size() - 2; i++) mins[i] = min(mins[i - 1], nums[i]);
        for (int i = nums.size() - 2; i >= 1; i--)
        {
            if (mins[i - 1] < nums[i] && nums[i + 1] > nums[i]) return true;
            nums[i] = max(nums[i + 1], nums[i]);
        }
        return false;
    }
};