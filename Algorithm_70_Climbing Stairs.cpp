#include <iostream>
#include <vector>
using namespace std;
int climbStairs(int n)
{
	if (n==1) return 1;
	else if (n==2) return 2;
	int t1=1;
	int t2=2;
	int tmp;
	for (int i=0;i<n-3;i++)
	{
		tmp=t1;
		t1=t2;
		t2+=tmp;
	}
	return t1+t2;
}
int main()
{
	cout<<climbStairs(3);
	return 0;
}
