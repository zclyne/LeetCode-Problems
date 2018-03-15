#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    int left=-1,right=-1;
    void getIndex(vector<int> &nums, int target, int low, int high)
    {
        if (low>high || high<0) return;
        if (low==high && nums[low]==target)
        {
            if (left==-1 && right==-1) left=right=low;
            else
            {
                if (low<left) left=low;
                if (low>right) right=low;
            }
            return;
        }
        int mid=(low+high)/2;
        if (nums[mid]==target)
        {
            if (left==-1 && right==-1) left=right=mid;
            if (mid<left) left=mid;
            if (mid>right) right=mid;
            getIndex(nums,target,low,mid-1);
            getIndex(nums,target,mid+1,high);
        }
        else if (nums[mid]<target) getIndex(nums,target,mid+1,high);
        else getIndex(nums,target,low,mid-1);
    }
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> res;
        if (nums.size()==0 || nums.size()==1 && nums[0]!=target)
        {
            res.push_back(-1);
            res.push_back(-1);
            return res;
        }
        else if (nums.size()==1 && nums[0]==target)
        {
            res.push_back(0);
            res.push_back(0);
            return res;
        }
        getIndex(nums,target,0,nums.size()-1);
        res.push_back(left);
        res.push_back(right);
        return res;
    }
};