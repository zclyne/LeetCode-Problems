#include <iostream>
#include <climits>
using namespace std;
long long int reverse(long long int x);
int main()
{
	long long int x=1534236469;
	cout<<reverse(x);
	return 0;
}
long long int reverse(long long int x) {
    long long int res=0;
    while (x!=0)
    {
        res=res*10+x%10;
        x/=10;
	}
	if (res<INT_MIN || res>INT_MAX) res=0;
	if (x<0) res=-res;
    return res;
}
