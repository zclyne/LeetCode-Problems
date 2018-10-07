#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>
using namespace std;
class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        if (!nums.size()) return 0;
        vector<int> dp(target + 1, -1);
        dp[0] = 1;
        return getCombination(nums, dp, target);
    }
    int getCombination(vector<int> &nums, vector<int> &dp, int target)
    {
        if (dp[target] != -1) return dp[target];
        int count = 0;
        for (auto num : nums) if (num <= target) count += getCombination(nums, dp, target - num);
        dp[target] =  count;
        return count;
    }
};

// bottom-up java solution
// public int combinationSum4(int[] nums, int target) {
//     int[] comb = new int[target + 1];
//     comb[0] = 1;
//     for (int i = 1; i < comb.length; i++) {
//         for (int j = 0; j < nums.length; j++) {
//             if (i - nums[j] >= 0) {
//                 comb[i] += comb[i - nums[j]];
//             }
//         }
//     }
//     return comb[target];
// }