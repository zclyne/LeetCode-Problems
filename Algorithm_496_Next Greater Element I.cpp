#include <vector>
#include <algorithm>
#include <climits>
#include <unordered_map>
using namespace std;
class Solution {
public:
    unordered_map<int,int> map;
    int lookForGreater(vector<int> &nums, int n)
    {
        int index=map[n];
        for (int i=index+1;i<nums.size();i++) if (nums[i]>n) return nums[i];
        return -1;
    }
    vector<int> nextGreaterElement(vector<int>& findNums, vector<int>& nums) {
        int max=INT_MIN;
        vector<int> result;
        if (findNums.size()==0) return result;
        for (int i=0;i<nums.size();i++)
        {
            if (nums[i]>max) max=nums[i];
            map[nums[i]]=i;
        }
        for (int i=0;i<findNums.size();i++)result.push_back(lookForGreater(nums,findNums[i]));
        return result;
    }
};