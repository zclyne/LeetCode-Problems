#include <iostream>
using namespace std;
string addBinary(string a, string b)
{
	string res,res2;
	int len1=a.length(),len2=b.length();
	int c1,c2,sum,carry=0;
	if (len1<len2) return addBinary(b,a);
	for (int i=len1-1;i>=0;i--)
	{
		c1=a[i]-'0';
		if (i<len1-len2) c2=0;
		else c2=b[i-(len1-len2)]-'0';
		sum=c1+c2+carry;
		if (sum==2)
		{
			carry=1;
			sum=0;
		}
		else if (sum==3)
		{
			carry=1;
			sum=1;
		}
		else carry=0;
		res+=sum+'0';
	}
	if (carry) res+='1';
	for (int i=res.length()-1;i>=0;i--) res2+=res[i];
	return res2;
}
int main()
{
	string a="0";
	string b="11";
	cout<<addBinary(a,b);
}
