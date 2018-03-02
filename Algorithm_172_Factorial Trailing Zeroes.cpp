#include <iostream>
#include <algorithm>
using namespace std;
int trailingZeroes(int n)
{
	int res=0;
    for (long long int i=5;n/i>0;i*=5) res+=n/i;
    return res;
}
