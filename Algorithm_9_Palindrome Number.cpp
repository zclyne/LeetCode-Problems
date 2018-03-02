#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;
bool isPalindrome(int x);
int main()
{
	int x=12021;
	cout<<isPalindrome(x);
	return 0;
}
bool isPalindrome(int x)
{
	if (x<0) return false;
	int len=1,tmp=x;
	while (tmp/10!=0)
	{
		len++;
		tmp/=10;
	}
	while (x!=0)
	{
		if (x%10!=int(x/pow(10,len-1))) return false;
		if (len==1 || len==0) return true;
		x/=10;
		len-=2;
		tmp=pow(10,len);
		x=x%tmp;
	}
	return true;
}
