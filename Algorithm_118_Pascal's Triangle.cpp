#include <iostream>
#include <vector>
using namespace std;
vector<vector<int> > generate(int numRows)
{
	vector<vector<int> > result;
	if (numRows==0) return result;
	vector<int> tmp;
	tmp.push_back(1);
	result.push_back(tmp);
	for (int i=1;i<numRows;i++)
	{
		tmp.clear();
		for (int j=0;j<=i;j++)
		{
			int left=(j==0)?0:result[i-1][j-1];
			int right=(j==i)?0:result[i-1][j];
			tmp.push_back(left+right);
		}
		result.push_back(tmp);
	}
	return result;
}
int main()
{
	vector<vector<int> >res=generate(2);
	for (int i=0;i<2;i++)
	{
		for (int j=0;j<=i;j++) cout<<res[i][j]<<' ';
		cout<<endl;
	}
	return 0;
}
