#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
using namespace std;
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums)
    {
        vector<int> ori;
        for (int i=0;i<nums.size();i++) ori.push_back(nums[i]);
        sort(nums.begin(),nums.end());
        int start,end;
        for (start=0;start<nums.size();start++) if (nums[start]!=ori[start]) break;
        for (end=nums.size()-1;end>=0;end--) if (nums[end]!=ori[end]) break;
        return end>start?end-start+1:0;
    }
};