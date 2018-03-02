#include <iostream>
using namespace std;
bool judgeCircle(string moves)
{
	char tmp;
	int len=moves.length();
	if (len==0) return false;
	int x=0,y=0;
	for (int i=0;i<len;i++)
	{
		tmp=moves[i];
		if (tmp=='U') y++;
		else if (tmp=='D') y--;
		else if (tmp=='L') x--;
		else x++;
	}
	if (x==0 && y==0) return true; 
	return false;
}
