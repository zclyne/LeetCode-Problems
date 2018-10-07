#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        if (!nums.size()) return 0;
        vector<int> posMaxLen(nums.size(), 1);
        vector<int> negMaxLen(nums.size(), 1);
        int res = 1;
        for (int i = 1; i < nums.size(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (nums[j] < nums[i] && 1 + negMaxLen[j] > posMaxLen[i]) posMaxLen[i] = 1 + negMaxLen[j];
                if (nums[j] > nums[i] && 1 + posMaxLen[j] > negMaxLen[i]) negMaxLen[i] = 1 + posMaxLen[j];
                int tmpMax = max(negMaxLen[i], posMaxLen[i]);
                res = max(tmpMax, res);
            }
        }
        return res;
    }
};

// better O(n) solution
// class Solution {
// public:
//     int wiggleMaxLength(vector<int>& nums) {
//         if (!nums.size()) return 0;
//         vector<int> up(nums.size(), 1);
//         vector<int> down(nums.size(), 1);
//         int res = 1;
//         for (int i = 1; i < nums.size(); i++)
//         {
//             if (nums[i] > nums[i - 1])
//             {
//                 up[i] = down[i - 1] + 1;
//                 down[i] = down[i - 1];
//             }
//             else if (nums[i] < nums[i - 1])
//             {
//                 up[i] = up[i - 1];
//                 down[i] = up[i - 1] + 1;
//             }
//             else
//             {
//                 up[i] = up[i - 1];
//                 down[i] = down[i - 1];
//             }
//         }
//         return max(up[nums.size() - 1], down[nums.size() - 1]);
//     }
// };