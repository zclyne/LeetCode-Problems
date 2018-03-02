#include <iostream>
#include <vector>
using namespace std;
int removeDuplicates(vector<int>& nums)
{
	int cur=1;
	while (cur<nums.size())
	{
		if (nums[cur]==nums[cur-1]) nums.erase(nums.begin()+cur);
		else cur++;
	}
	return nums.size();
}
int main()
{
	vector<int> nums;
	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(3);
	nums.push_back(5);
	nums.push_back(5);
	cout<<removeDuplicates(nums);
	return 0;
}
