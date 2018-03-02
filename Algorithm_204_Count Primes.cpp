#include <iostream>
#include <cmath>
using namespace std;
bool isPrime(double n)
{
	if (n==1) return false;
	if (n==2) return true;
	for (int i=2;i*i<n;i++)
	{
		if (n/i-int(n/i)==0) return false;
	}
	return true;
}
int countPrimes(int n)
{
	if (n<=2) return 0;
	int count=0;
	for (int i=2;i<n;i++)
	{
		if (isPrime(i)) count++;
	}
	return count;
}
int main()
{
	cout<<countPrimes(7);
	return 0;
}
