#include <iostream>
#include <vector>
#include <unordered_map> 
using namespace std;
int findLHS(vector<int>& nums)
{
	int size=nums.size();
	if (size==0) return 0;
	int max=0,cur=0;
	unordered_map<int,int> map;
	for (int i=0;i<size;i++) map[nums[i]]++;
	for (int i=0;i<size;i++)
	{
		if (map.find(nums[i]-1)!=map.end())
		{
			cur=map[nums[i]]+map[nums[i]-1];
			if (cur>max) max=cur;
		}
	}
	return max;
}
int main()
{
	vector<int> nums;
	nums.push_back(1);
	nums.push_back(3);
	nums.push_back(2);
	nums.push_back(2);
	nums.push_back(5);
	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(7);
	cout<<findLHS(nums);
	return 0;
}
