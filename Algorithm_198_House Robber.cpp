#include <iostream>
#include <vector>
using namespace std;
int rob(vector<int>& nums)
{
	int a = 0;
    int b = 0;
    int size=nums.size();
    for (int i=0; i<size; i++)
    {
        if (i%2==0)
        {
            a = (a+nums[i]>b)?a+nums[i]:b;
        }
        else
        {
            b = (a>b+nums[i])?a:b+nums[i];
        }
    }
	return (a>b)?a:b;
}
