#include <iostream>
using namespace std;
int lengthOfLastWord(string s)
{
	int len=s.length()-1,res=0;
	while (s[len]==' ') len--;
	for (int i=len;i>=0;i--)
	{
		if (s[i]==' ') break;
		else res++;
	}
	return res;
} 
int main()
{
	string s="a world";
	cout<<lengthOfLastWord(s);
	return 0;
}
