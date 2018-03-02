#include <iostream>
using namespace std;
bool isIsomorphic(string s, string t)
{
	int len=s.length();
	char map1[128],map2[128];
	for (int i=0;i<128;i++)
	{
		map1[i]=' ';
		map2[i]=' ';
	}
	int tmp1,tmp2;
	for (int i=0;i<len;i++)
	{
		tmp1=(int)s[i];
		tmp2=(int)t[i];
		if (map1[tmp1]==' ' && map2[tmp2]==' ') //a new char
		{
			 map1[tmp1]=t[i];
			 map2[tmp2]=s[i];
		}
		else if (map1[tmp1]!=t[i] || map2[tmp2]!=s[i]) return false; //already mapped
	}
	return true;
}
int main()
{
	string s="ab";
	string t="aa";
	cout<<isIsomorphic(s,t);
	return 0;
}
