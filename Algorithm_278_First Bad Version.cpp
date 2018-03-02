#include <iostream>
using namespace std;
bool isBadVersion(int version)
{
	return version==1 || version==2;
}
int firstBadVersion(int n)
{
	int head=1,end=n;
	int mid,pos=n;
	while (head<end)
	{
		mid=(head+end)/2;
		//cout<<"mid="<<mid<<' ';
		if (isBadVersion(mid))
		{
			pos=mid;
			end=mid;
		}
		else head=mid+1;
	}
	return pos;
}

int main()
{
	cout<<firstBadVersion(3);
}
