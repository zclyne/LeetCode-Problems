#include <iostream>
#include <vector>
using namespace std;
vector<int> plusOne(vector<int>& digits)
{
	int len=digits.size(),carry=0;
	digits[len-1]+=1;
	for (int i=len-1;i>=0;i--)
	{
		digits[i]+=carry;
		if (digits[i]==10)
		{
			digits[i]=0;
			carry=1;
		}
		else carry=0;
	}
	if (carry==1) digits.insert(digits.begin(),1);
	return digits;
}
int main()
{
	vector<int> num;
	num.push_back(0);
	//num.push_back(9);
	num=plusOne(num);
	for (int i=0;i<num.size();i++) cout<<num[i];
	return 0;
}
