#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;
bool containsNearbyDuplicate(vector<int>& nums, int k)
{
	set<int> map;
    int size=nums.size();
    for (int i=0;i<size;i++)
    {
        if (i>k) map.erase(nums[i-k-1]);
        if (!map.insert(nums[i]).second) return true;
    }
    return false;
}
