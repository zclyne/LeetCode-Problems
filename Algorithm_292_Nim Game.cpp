#include <iostream>
using namespace std;
bool canWinNim(int n)
{
	/*if (n<=3) return true;
	return !(canWinNim(n-1) && canWinNim(n-2) && canWinNim(n-3));*/
	return n%4!=0;
}

