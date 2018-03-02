#include <iostream>
using namespace std;
bool isAnagram(string s, string t)
{
	int len1=s.length(),len2=t.length();
	if (len1!=len2) return false;
	int c1[26],c2[26];
	for (int i=0;i<26;i++) c1[i]=c2[i]=0;
	for (int i=0;i<len1;i++)
	{
		c1[s[i]-'a']++;
		c2[t[i]-'a']++;
	}
	for (int i=0;i<26;i++) if (c1[i]!=c2[i]) return false;
	return true;
}
int main()
{
	string s="add";
	string t="dda";
	cout<<isAnagram(s,t);
}
