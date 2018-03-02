#include <iostream>
#include <vector>
using namespace std;
int maxSubArray(vector<int>& nums)
{
	int thisSum=0,maxSum=nums[0],len=nums.size();
	for (int i=0;i<len;i++)
	{
		thisSum+=nums[i];
		if (thisSum>maxSum) maxSum=thisSum;
		if (thisSum<0) thisSum=0;
	}
	return maxSum;
}
int main()
{
	vector<int> nums;
	nums.push_back(-1);
	cout<<maxSubArray(nums);
}
