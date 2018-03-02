#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int missingNumber(vector<int>& nums)
{
	sort(nums.begin(),nums.end());
	int size=nums.size();
	for (int i=0;i<size-1;i++) if (nums[i+1]!=nums[i]+1) return nums[i]+1;
	return 0;
}
int main()
{
	vector<int> nums;
	nums.push_back(3);
	nums.push_back(0);
	nums.push_back(1);
	cout<<missingNumber(nums);
	if (nums[0]==0) return size;
	else return 0;
}
