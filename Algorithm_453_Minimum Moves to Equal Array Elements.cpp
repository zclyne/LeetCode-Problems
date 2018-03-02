#include <vector>
#include <algorithm>
#include <climits>
using namespace std;
class Solution {
public:
    /*void swap(vector<int> &nums, int index1, int index2)
    {
        int tmp=nums[index1];
        nums[index1]=nums[index2];
        nums[index2]=tmp;
    }
    void increment(vector<int> &nums)
    {
        for (int i=0;i<nums.size()-1;i++) nums[i]++;
    }
    void adjust(vector<int> &nums)
    {
        for (int i=nums.size()-1;i>0 && nums[i] < nums[i-1];i--) swap(nums, i, i-1);
    }
    int minMoves(vector<int>& nums) {
        int len=nums.size(), count=0;
        sort(nums.begin(), nums.end());
        if (len==2) return nums[1]-nums[0];
        while (nums[0]!=nums[len-1])
        {
            increment(nums);
            adjust(nums);
            count++;
        }
        return count;
    }*/
    int minMoves(vector<int>& nums)
    {
        int sum=0, min=INT_MAX;
        for (int i=0;i<nums.size();i++)
        {
            sum+=nums[i];
            if (nums[i]<min) min=nums[i];
        }
        return sum-min*nums.size();
    }
};