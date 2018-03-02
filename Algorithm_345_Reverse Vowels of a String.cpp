#include <iostream>
#include <stack>
#include <queue>
using namespace std;
string reverseVowels(string s)
{
	stack<char> sta;
	queue<int> pos;
	int len=s.length();
	for (int i=0;i<len;i++)
	{
		if (isVowel(s[i]))
		{
			sta.push(s[i]);
			pos.push(i);
		}
	}
	while (!pos.empty())
	{
		s[pos.front()]=sta.top();
		sta.pop();
		pos.pop();
	}
	return s;
}
bool isVowel(char c) {return c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='A' || c=='E' || c=='I' || c=='O' || c=='U';}
