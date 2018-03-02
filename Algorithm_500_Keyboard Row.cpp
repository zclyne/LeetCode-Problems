#include <iostream>
#include <vector>
#include <map>
using namespace std;
bool judge(string s, map<char,int> row)
{
	int len=s.length();
	for (int i=1;i<len;i++)
	{
		if (row.find(s[i])==row.end()) return false;
	}
	return true;
}
vector<string> findWords(vector<string>& words)
{
	int size=words.size();
	int mark;
	string cur;
	map<char,int> row1,row2,row3;
	vector<string> res;
	string s1_l="qwertyuiop",s1_u="QWERTYUIOP";
	string s2_l="asdfghjkl",s2_u="ASDFGHJKL";
	string s3_l="zxcvbnm",s3_u="ZXCVBNM";
	for (int i=0;i<10;i++) row1[s1_l[i]]=i;
	for (int i=10;i<20;i++) row1[s1_u[i-10]]=i;
	for (int i=0;i<9;i++) row2[s2_l[i]]=i;
	for (int i=9;i<18;i++) row2[s2_u[i-9]]=i;
	for (int i=0;i<7;i++) row3[s3_l[i]]=i;
	for (int i=7;i<14;i++) row3[s3_u[i-7]]=i;
	for (int i=0;i<size;i++)
	{
		cur=words[i];
		if (row1.find(cur[0])!=row1.end())
		{
			if (judge(cur,row1)) res.push_back(cur);
		}
		else if (row2.find(cur[0])!=row2.end())
		{
			if (judge(cur,row2)) res.push_back(cur);
		}
		else
		{
			if (judge(cur,row3)) res.push_back(cur);
		}
	}
	return res;
}
int main()
{
	vector<string> vec;
	vec.push_back("Hello");
	vec.push_back("Alaska");
	vec.push_back("Dad");
	vec.push_back("Peace");
	vector<string> res;
	res=findWords(vec);
	for (int i=0;i<res.size();i++) cout<<res[i];
	return 0;
}
