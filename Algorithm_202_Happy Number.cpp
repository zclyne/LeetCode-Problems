#include <iostream>
#include <vector>
using namespace std;
int square(int n)
{
	int sum=0;
	while (n)
	{
		sum+=(n%10)*(n%10);
		n/=10;
	}
	return sum;
}
bool isHappy(int n)
{
    int sum=n;
    int slow=0;
    int fast=square(n);
    while (slow!=fast)
    {
    	slow=square(slow);
    	fast=square(fast);
    	fast=square(fast);
	}
	if (fast==1) return true;
	else return false;
}
