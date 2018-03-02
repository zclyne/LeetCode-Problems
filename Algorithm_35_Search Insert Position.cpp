#include <iostream>
#include <vector>
using namespace std;
int searchInsert(vector<int>& nums, int target)
{
	if (target<=nums[0]) return 0;
	int l=nums.size();
	for (int i=0;i<l;i++)
	{
		if (nums[i-1]<target && nums[i]>=target) return i;
	}
	return l;
}
int main()
{
	vector<int> nums;
	nums.push_back(1);
	nums.push_back(3);
	nums.push_back(5);
	nums.push_back(6);
	cout<<searchInsert(nums, 0);
	return 0;
}
