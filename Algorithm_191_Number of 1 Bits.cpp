#include <iostream>
using namespace std;
int hammingWeight(uint32_t n)
{
	int count=0,r;
	while (n!=0)
	{
		r=n%2;
		n/=2;
		if (r) count++;
	}
	return count;
}
