#include <iostream>
#include <stack>
using namespace std;
bool isValid(string s)
{
	stack<char> sta;
	for (int i=0;i<s.length();i++)
	{
		if (s[i]=='(' || s[i]=='[' || s[i]=='{') sta.push(s[i]);
		else if (s[i]==')')
		{
			if (sta.size()==0 || sta.top()!='(') return false;
			else sta.pop();
		}
		else if (s[i]==']')
		{
			if (sta.size()==0 || sta.top()!='[') return false;
			else sta.pop();
		}
		else
		{
			if (sta.size()==0 || sta.top()!='{') return false;
			else sta.pop();
		}
	}
	if (sta.size()==0) return true;
	else return false; 
}
int main()
{
	string s="]";
	cout<<isValid(s);
	return 0;
}
