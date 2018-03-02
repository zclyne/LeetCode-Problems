#include <iostream>
#include <vector>
using namespace std;
int removeElement(vector<int>& nums, int val)
{
	int i=0;
	int n = nums.size();
    while (i < n)
	{
        if (nums[i] == val)
		{
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } 
		else i++;
    }
    return n;
}
int main()
{
	vector<int> nums;
	nums.push_back(3);
	nums.push_back(2);
	nums.push_back(2);
	nums.push_back(3);
	cout<<removeElement(nums,3);
	return 0;
}
