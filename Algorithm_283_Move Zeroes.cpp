#include <iostream>
#include <vector>
using namespace std;
void moveZeroes(vector<int>& nums)
{
	int size=0;
    int tmp=0;
    while (size<nums.size())
    {
        if (nums[size]==0)
        {
            nums.erase(nums.begin()+size);
            tmp++;
        }
        else size++;
    }
    for (int i=0;i<tmp;i++) nums.push_back(0);
}

//method 2
void moveZeroes(vector<int>& nums)
{
    int cur=0,last=0,size=nums.size();
    while (cur<size)
    {
        if (nums[cur]!=0)
        {
            swap(nums[cur],nums[last]);
            last++;
        }
        cur++;
    }
}
