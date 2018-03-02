#include <iostream>
#include <vector>
using namespace std;
int findMaxConsecutiveOnes(vector<int>& nums)
{
    int count=0,size=nums.size();
    int max=0;
    for (int i=0;i<size;i++)
    {
        if (nums[i]==0) count=0;
        else count++;
        if (count>max) max=count;
    }
    return max;
}