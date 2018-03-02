#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<int> intersection(vector<int>& nums1, vector<int>& nums2)
{
	sort(nums1.begin(),nums1.end());
	sort(nums2.begin(),nums2.end());
	int p1=0,p2=0,len1=nums1.size(),len2=nums2.size();
	vector<int> res;
	if (len1==0 || len2==0) return res;
	while (p1<len1 && p2<len2)
	{
		if (nums1[p1]==nums2[p2] && (res.size()==0 || nums1[p1]!=res.back()))
		{
			res.push_back(nums1[p1]);
			p1++;
			p2++;
		}
		else if (nums1[p1]>nums2[p2]) p2++;
		else p1++;
	}
	return res;
}
int main()
{
	vector<int> n1, n2;
	n1.push_back(1);
	n2.push_back(1);
	n2.push_back(1);
	vector<int> res=intersection(n1,n2);
	for (int i=0;i<res.size();i++) cout<<res[i]<<' ';
	return 0;
}
