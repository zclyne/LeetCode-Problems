#include <iostream>
#include <vector>
using namespace std;
void merge(vector<int>& nums1, int m, vector<int>& nums2, int n)
{
	int p1=0,p2=0;
	while (p1<m-1 && p2<n)
	{
		if (nums1[p1]<=nums2[p2] && nums1[p1+1]>nums2[p2])
		{
			nums1.insert(nums1.begin()+p1+1,nums2[p2]);
			p2++;
		}
		p1++;
	}
	if (p1==m-2 && p2<n-1)
	{
		for (int i=p2;i<n;i++) nums1.insert(nums1.begin()+i-p2,nums2[i]);
	}
}
int main()
{
	int a[5]={1,3,4,5,10};
	int b[2]={2,7};
	vector<int> nums1;
	vector<int> nums2;
	for (int i=0;i<5;i++) nums1.push_back(a[i]);
	for (int i=0;i<2;i++) nums2.push_back(b[i]);
	merge(nums1,nums1.size(),nums2,nums2.size());
	for (int i=0;i<7;i++) cout<<nums1[i]<<' ';
}
