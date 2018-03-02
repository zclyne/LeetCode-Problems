#include <iostream>
#include <vector>
using namespace std;
/*vector<int> intersect(vector<int>& nums1, vector<int>& nums2)
{
	int len1=nums1.size(),len2=nums2.size();
	if (len1<len2) return intersect(nums2,nums1);
	vector<int> res;
	if (len1==0 || len2==0) return res;
	bool flag1[len1],flag2[len2];
	for (int i=0;i<len1;i++) flag1[i]=false;
	for (int i=0;i<len2;i++) flag2[i]=false;
	for (int i=0;i<len2;i++)
	{
		for (int j=0;j<len1;j++)
		{
			if (nums2[i]==nums1[j] && !flag1[j] && !flag2[i])
			{
				res.push_back(nums2[i]);
				flag1[j]=true;
				flag2[i]=true;
			}
		}
	}
	return res;
}*/

class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> dict;
        vector<int> res;
        for(int i = 0; i < (int)nums1.size(); i++) dict[nums1[i]]++;
        for(int i = 0; i < (int)nums2.size(); i++)
            if(--dict[nums2[i]] >= 0) res.push_back(nums2[i]);
        return res;
    }
};
int main()
{
	vector<int> n1, n2;
	n1.push_back(1);
	n2.push_back(1);
	n2.push_back(1);
	vector<int> res=intersect(n1,n2);
	for (int i=0;i<res.size();i++) cout<<res[i]<<' ';
	return 0;
}
