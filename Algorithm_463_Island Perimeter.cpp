#include <iostream>
#include <vector>
using namespace std;
int islandPerimeter(vector<vector<int>>& grid)
{
	int width=grid[0].size(),height=grid.size();
	if (width==0 || height==0) return 0;
	bool up,down,left,right;
	int count,perimeter=0;
	for (int i=0;i<height;i++)
	{
		for (int j=0;j<width;j++)
		{
			if (grid[i][j]==1)
			{
				count=0;
				if (i-1<0 || grid[i-1][j]==0) count++;
				if (i+1==height || grid[i+1][j]==0) count++;
				if (j-1<0 || grid[i][j-1]==0) count++;
				if (j+1==width || grid[i][j+1]==0) count++;
				perimeter+=count;
			}
		}
	}
	return perimeter;
}
