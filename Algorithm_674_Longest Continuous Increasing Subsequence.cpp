#include <iostream>
#include <vector>
using namespace std;
int findLengthOfLCIS(vector<int>& nums)
{
	int max=1,size=nums.size(),cur=1;
	if (size==0) return 0;
	for (int i=1;i<size;i++)
	{
		if (nums[i]>nums[i-1]) cur++;
		else cur=1;
		if (cur>max) max=cur;
	}
	return max;
}
int main()
{
	vector<int> nums;
	nums.push_back(1);
	nums.push_back(3);
	nums.push_back(5);
	nums.push_back(4);
	nums.push_back(7);
	cout<<findLengthOfLCIS(nums);
	return 0;
}
