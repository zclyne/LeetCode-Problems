#include <iostream>
#include <vector>
#include <set>
using namespace std;
int singleNumber(vector<int>& nums)
{
	int size=nums.size();
    set<int> s;
    set<int>::iterator it;
    for (int i=0;i<size;i++)
    {
       it=s.find(nums[i]);
        if (it!=s.end()) s.erase(nums[i]);
        else s.insert(nums[i]);
    }
    return *s.begin();
}

