#include <iostream>
#include <vector>
using namespace std;
void rotate(vector<int>& nums, int k)
{
	int size=nums.size();
	for (int i=0;i<size-k%size;i++)
	{
		nums.push_back(nums[0]);
		nums.erase(nums.begin());
	}
	
}
