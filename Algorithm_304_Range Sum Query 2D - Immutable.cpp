#include <iostream>
#include <vector>
using namespace std;
class NumMatrix
{
private:
    vector<vector<int>> mySumMatrix;
public:
    NumMatrix(vector<vector<int>> matrix)
    {
        int rowLen = matrix.size();
        int colLen = rowLen > 0 ? matrix[0].size() : 0;
        vector<int> tmp(colLen + 1, 0);
        for (int i = 0; i < rowLen + 1; i++) mySumMatrix.push_back(tmp);
        for (int i = 0; i < rowLen; i++) for (int j = 0; j < colLen; j++) mySumMatrix[i + 1][j + 1] = mySumMatrix[i][j + 1] + mySumMatrix[i + 1][j] - mySumMatrix[i][j] + matrix[i][j];
    }

    int sumRegion(int row1, int col1, int row2, int col2)
    {
        return mySumMatrix[row2 + 1][col2 + 1] + mySumMatrix[row1][col1] - mySumMatrix[row1][col2 + 1] - mySumMatrix[row2 + 1][col1];
    }
};