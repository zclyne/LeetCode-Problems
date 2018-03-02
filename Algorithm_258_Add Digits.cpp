#include <iostream>
using namespace std;
int addDigits(int num)
{
	int tmp=num;
	int sum=num;
	while (tmp/10>0)
	{
		sum=0;
		while (tmp!=0)
		{
			sum+=tmp%10;
			tmp/=10;
		}
		tmp=sum;
	}
	return sum;
}
int main()
{
	cout<<addDigits(38);
	return 0;
}
