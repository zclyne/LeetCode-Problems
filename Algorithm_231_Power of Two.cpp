#include <iostream>
using namespace std;
bool isPowerOfTwo(int n)
{
	while (n%2==0) n/=2;
	return n==1;
}
int main()
{
	cout<<isPowerOfTwo(8);
}
