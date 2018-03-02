#include <iostream>
#include <vector>
using namespace std;
vector<int> getRow(int rowIndex)
{
	vector<int> tmp;
	tmp.push_back(1);
	if (rowIndex==0) return tmp;
	for (int i=1;i<=rowIndex;i++)
	{
		vector<int> last(tmp.begin(),tmp.end());
		tmp.clear();
		for (int j=0;j<=i;j++)
		{
			int left=(j==0)?0:last[j-1];
			int right=(j==i)?0:last[j];
			tmp.push_back(left+right); 
		}
	}
	return tmp;
}
