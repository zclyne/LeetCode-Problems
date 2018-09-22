#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int res = 0;
        if (grid.size() == 0) return res;
        vector<vector<bool> > hasVisited(grid.size());
        vector<bool> tempRow(grid[0].size());
        for (int i = 0 ; i < grid[0].size() ; i++) tempRow[i] = false;
        for (int i = 0 ; i < grid.size() ; i++) hasVisited[i] = tempRow;
        for (int i = 0 ; i < grid.size() ; i++)
        {
            for (int j = 0; j < grid[0].size() ; j++)
            {
                if (grid[i][j] == '1' && !hasVisited[i][j])
                {
                    getIsland(grid, hasVisited, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    void getIsland(vector<vector<char> > &grid, vector<vector<bool> > &hasVisited, int i, int j)
    {
        if (grid[i][j] == '0') return;
        hasVisited[i][j] = true;
        if (i > 0 && !hasVisited[i - 1][j]) getIsland(grid, hasVisited, i - 1, j);
        if (i < grid.size() - 1 && !hasVisited[i + 1][j]) getIsland(grid, hasVisited, i + 1, j);
        if (j > 0 && !hasVisited[i][j - 1]) getIsland(grid, hasVisited, i, j - 1);
        if (j < grid[0].size() - 1 && !hasVisited[i][j + 1]) getIsland(grid, hasVisited, i, j + 1);
        return;
    }
};

// better solution
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int res = 0;
        if (grid.size() == 0) return res;
        for (int i = 0 ; i < grid.size() ; i++)
        {
            for (int j = 0; j < grid[0].size() ; j++)
            {
                if (grid[i][j] == '1')
                {
                    getIsland(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    void getIsland(vector<vector<char> > &grid, int i, int j)
    {
        if (grid[i][j] == '0') return;
        grid[i][j] = '0';
        if (i > 0 && grid[i - 1][j] == '1') getIsland(grid, i - 1, j);
        if (i < grid.size() - 1 && grid[i + 1][j] == '1') getIsland(grid, i + 1, j);
        if (j > 0 && grid[i][j - 1] == '1') getIsland(grid, i, j - 1);
        if (j < grid[0].size() - 1 && grid[i][j + 1] == '1') getIsland(grid, i, j + 1);
        return;
    }
};