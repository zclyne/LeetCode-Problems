#include <iostream>
#include <string>
#include <cmath>
using namespace std;
int titleToNumber(string s)
{
	int len=s.length();
    int res=0;
    for (int i=0;i<len;i++) res+=(s[i]-'A'+1)*pow(26,len-i-1);
    return res;
}
int main()
{
	string s="AZ";
	cout<<titleToNumber(s);
	return 0;
}
