#include <iostream>
#include <vector>
using namespace std;
void dfs(vector<vector<int> > &grid, int **visited, int i, int j,int &area,int height, int width)
{
	area++;
	visited[i][j]=1;
	if (i<height-1 && grid[i+1][j] && !visited[i+1][j]) dfs(grid,visited,i+1,j,area,height,width);
	if (i>0 && grid[i-1][j] && !visited[i-1][j]) dfs(grid,visited,i-1,j,area,height,width);
	if (j<width-1 && grid[i][j+1] && !visited[i][j+1]) dfs(grid,visited,i,j+1,area,height,width);
	if (j>0 && grid[i][j-1] && !visited[i][j-1]) dfs(grid,visited,i,j-1,area,height,width);
}
int getArea(vector<vector<int> > &grid,int **visited,int i, int j)
{
	int area=0;
	int height=grid.size(),width=grid[0].size();
	dfs(grid,visited,i,j,area,height,width);
	return area;
}
int maxAreaOfIsland(vector<vector<int> >& grid)
{
	int height=grid.size(),width=grid[0].size();
	if (height==0 || width==0) return 0;
	int max=0,cur=0;
	int **visited;
	visited=new int*[height];
	for (int i=0;i<height;i++) visited[i]=new int[width];
	for (int i=0;i<height;i++) for (int j=0;j<width;j++) visited[i][j]=0;
	for (int i=0;i<height;i++)
	{
		for (int j=0;j<width;j++)
		{
			if (grid[i][j] && !visited[i][j]) cur=getArea(grid,visited,i,j);
			if (cur>max) max=cur;
		}
	}
	return max;
}
int main()
{
	vector<vector<int> > grid;
	vector<int> tmp;
	grid.push_back(tmp);
	grid.push_back(tmp);
	grid.push_back(tmp);
	grid.push_back(tmp);
	grid[0].push_back(1);
	grid[0].push_back(1);
	grid[0].push_back(0);
	grid[0].push_back(0);
	grid[0].push_back(0);
	grid[1].push_back(1);
	grid[1].push_back(1);
	grid[1].push_back(0);
	grid[1].push_back(0);
	grid[1].push_back(0);
	grid[2].push_back(0);
	grid[2].push_back(0);
	grid[2].push_back(0);
	grid[2].push_back(1);
	grid[2].push_back(1);
	grid[3].push_back(0);
	grid[3].push_back(0);
	grid[3].push_back(0);
	grid[3].push_back(1);
	grid[3].push_back(1);
	cout<<maxAreaOfIsland(grid);
	return 0;
}
