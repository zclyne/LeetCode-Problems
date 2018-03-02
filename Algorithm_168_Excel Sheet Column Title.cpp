#include <iostream>
#include <string>
using namespace std;
string convertToTitle(int n)
{
	if (n==0) return "";
	string tmp,res;
	int r;
	while (n!=0)
	{
		r=n%26;
		n/=26;
		if (r>0) tmp+=char('A'+r-1);
		else
		{
			n--;
			tmp+='Z';
		}
	}
	for (int i=tmp.length()-1;i>=0;i--) res+=tmp[i];
	return res;
}
int main()
{
	cout<<convertToTitle(26);
	return 0;
}
