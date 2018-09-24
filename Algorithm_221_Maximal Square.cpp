#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution
{
  public:
    int maximalSquare(vector<vector<char>> &matrix)
    {
        int maxArea = 0;
        for (int i = 0; i < matrix.size(); i++)
            for (int j = 0; j < matrix[0].size(); j++)
                if (matrix[i][j] == '1')
                    maxArea = max(maxArea, getMaxArea(matrix, i, j));
        return maxArea;
    }
    int getMaxArea(vector<vector<char> > &matrix, int x, int y)
    {
        if (x == matrix.size() - 1 || y == matrix[0].size() - 1) return 1;
        int len = 1, curArea = 1;
        while (x + len < matrix.size() && y + len < matrix[0].size())
        {
            bool bottomSide = true, rightSide = true;
            for (int i = x; i <= x + len - 1; i++) if (matrix[i][y + len] == '0') {bottomSide = false; break;}
            for (int j = y; j <= y + len - 1; j++) if (matrix[x + len][j] == '0') {rightSide = false; break;}
            bool isSquare = bottomSide && rightSide && matrix[x + len][y + len] == '1';
            if (!isSquare) return curArea; // cannot be a square anymore
            // still can be a square
            curArea = (len + 1) * (len + 1);
            len++;
        }
        return curArea;
    }
};

// better DP solution
class Solution
{
  public:
    int maximalSquare(vector<vector<char>> &matrix)
    {
        if (matrix.empty())
        {
            return 0;
        }
        int m = matrix.size(), n = matrix[0].size(), sz = 0;
        vector<vector<int>> dp(m, vector<int>(n, 0));
        for (int j = 0; j < n; j++)
        {
            dp[0][j] = matrix[0][j] - '0';
            sz = max(sz, dp[0][j]);
        }
        for (int i = 1; i < m; i++)
        {
            dp[i][0] = matrix[i][0] - '0';
            sz = max(sz, dp[i][0]);
        }
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (matrix[i][j] == '1')
                {
                    dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    sz = max(sz, dp[i][j]);
                }
            }
        }
        return sz * sz;
    }
};