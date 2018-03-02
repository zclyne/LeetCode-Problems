#include <iostream>
using namespace std;
string reverseString(string s)
{
	string res="";
	int len=s.length();
	for (int i=len-1;i>=0;i--) res+=s[i];
	return res;
}
