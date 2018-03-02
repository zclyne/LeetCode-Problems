#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int majorityElement(vector<int>& nums)
{
	int size=nums.size(),count=0;
	if (size==1) return nums[0];
	sort(nums.begin(),nums.end());
	for (int i=0;i<size-1;i++)
	{
		if (nums[i+1]==nums[i]) count++;
		if (count>=size/2) return nums[i];
	}
}
