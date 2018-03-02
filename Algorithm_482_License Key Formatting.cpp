#include <iostream>
using namespace std;
string licenseKeyFormatting(string s, int k)
{
	int len=s.length();
	int count_dash=0;
	for (int i=0;i<len;i++)
	{
		if (s[i]=='-') count_dash++;
		else if (s[i]>='a' && s[i]<='z') s[i]=char(s[i]+'A'-'a');//convert lower to upper
	}
	int count_char=len-count_dash;
	int lenFirst=count_char%k;
	string res="";
	int count=0,i;
	for (i=0;count<lenFirst;i++)
	{
		if (s[i]!='-')
		{
			res+=s[i];
			count++;
		}
	}
	if (count>0 && count==count_char-1) return res;
	else if (count>0) res+='-';
	count=0;
	for (;i<len;i++)
	{
		if (count<k)
		{
			if (s[i]!='-')
			{
				res+=s[i];
				count++;
			}
		}
		else
		{
			res+='-';
			count=0;
			i--;
		}
	}
	if (res[res.length()-1]=='-') res.erase(res.begin()+res.length()-1);
	return res;
}
int main()
{
	string s="2";
	cout<<licenseKeyFormatting(s,2);
}
