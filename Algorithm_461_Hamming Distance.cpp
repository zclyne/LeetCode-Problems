#include <iostream>
#include <cmath>
using namespace std;
string convertToBinary(int x)
{
	string res="";
	while (x!=0)
	{
		res=char(x%2+'0')+res;
		x/=2;
	}
	return res;
}
int hammingDistance(int x, int y)
{
	string s1=convertToBinary(x);
	string s2=convertToBinary(y);
	int l1=s1.length(),l2=s2.length(),count=0;
	if (l2>l1) return hammingDistance(y,x);
	char c1,c2;
	for (int i=0;i<l1;i++)
	{
		c1=s1[l1-1-i];
		c2=i<l2?s2[l2-1-i]:'0';
		if (c1!=c2) count++;
	}
	return count;
}
int main()
{
	cout<<hammingDistance(1,4);
	return 0;
}
