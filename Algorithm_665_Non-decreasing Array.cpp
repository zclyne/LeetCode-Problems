#include <iostream>
#include <vector>
using namespace std;
bool checkPossibility(vector<int>& nums)
{
    bool flag=false;
    int size=nums.size();
    for (int i=0;i<size-1;i++)
    {
        if (nums[i]>nums[i+1])
        {
            if (flag) return false;
            else
            {
                if (i==0 || (i>=1 && nums[i-1]<=nums[i+1])) nums[i]=nums[i+1];
                else if (i==size-2 || (i<size-2 && nums[i]<=nums[i+2])) nums[i+1]=nums[i+2];
                else return false;
                flag=true;
            }
        }
    }
    return true;
}