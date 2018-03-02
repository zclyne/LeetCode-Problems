#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
bool containsDuplicate(vector<int>& nums)
{
	int size=nums.size();
	sort(nums.begin(),nums.end());
    for (int i=1;i<size;i++) if (nums[i-1]==nums[i]) return true;
    return false;
}
