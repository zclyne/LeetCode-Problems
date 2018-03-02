#include <iostream>
using namespace std;
int strStr(string haystack, string needle)
{
	int m=haystack.length(),n=needle.length();
	if (n==0) return 0;
	bool flag;
	for (int i=0;i<m-n+1;i++)
	{
		if (haystack[i]==needle[0])
		{
			flag=true;
			for (int j=0;j<n;j++)
			{
				if (haystack[i+j]!=needle[j])
				{
					flag=false;
					break;
				}
			}
		}
		if (flag) return i;
	}
	return -1;
}
int main()
{
	string a="aaaaa", needle="bba";
	cout<<strStr(a,needle);
	return 0;
}
