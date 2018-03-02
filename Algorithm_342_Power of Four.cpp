#include <iostream>
using namespace std;
bool isPowerOfFour(int num)
{
	if (num<=0) return false;
	while (num%4==0) num/=4;
	return num==1;
}
