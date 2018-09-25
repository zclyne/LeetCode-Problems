#include <iostream>
#include <vector>
using namespace std;

class Solution
{
  public:
    bool searchMatrix(vector<vector<int>> &matrix, int target)
    {
        if (matrix.size() == 0 || matrix[0].size() == 0) return false;
        int startRow = 0, endRow = matrix.size() - 1, startCol = 0, endCol = matrix[0].size() - 1;
        while (startRow < endRow && startCol < endCol)
        {
            if (matrix[startRow][startCol] == target || matrix[startRow][endCol] == target || matrix[endRow][startCol] == target ||  matrix[endRow][endCol] == target) return true;
            if (matrix[startRow][endCol] < target) startRow++;
            if (matrix[startRow][endCol] > target) endCol--;
            if (matrix[endRow][startCol] > target) endRow--;
            if (matrix[endRow][startCol] < target) startCol++;
        }
        if (startRow == endRow)
        {
            for (int i = startCol; i <= endCol; i++) if (matrix[startRow][i] == target) return true;
            return false;
        }
        if (startCol == endCol)
        {
            for (int i = startRow; i <= endRow; i++) if (matrix[i][startCol] == target) return true;
            return false;
        }
        return false;
    }
};

// another better solution
class Solution
{
  public:
    bool searchMatrix(vector<vector<int>> &matrix, int target)
    {
        if (matrix.size() == 0 || matrix[0].size() == 0) return false;
        int r = matrix.size() - 1, c = 0;
        while (r >= 0 && c <= matrix[0].size() - 1)
        {
            if (matrix[r][c] == target) return true;
            else if (matrix[r][c] > target) r--;
            else c++;
        }
        return false;
    }
};