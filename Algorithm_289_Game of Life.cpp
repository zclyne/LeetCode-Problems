#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    void gameOfLife(vector<vector<int>> &board)
    {
        if (board.size() == 0 || board[0].size() == 0) return;
        vector<vector<int> > lastState = board;
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
            {
                int count = 0;
                if (i > 0)
                {
                    if (lastState[i - 1][j] == 1) count++;
                    if (j > 0 && lastState[i - 1][j - 1] == 1) count++;
                    if (j < lastState[0].size() - 1 && lastState[i - 1][j + 1] == 1) count++;
                }
                if (i < board.size() - 1)
                {
                    if (lastState[i + 1][j] == 1) count++;
                    if (j > 0 && lastState[i + 1][j - 1] == 1) count++;
                    if (j < lastState[0].size() - 1 && lastState[i + 1][j + 1] == 1) count++;
                }
                if (j > 0 && lastState[i][j - 1] == 1) count++;
                if (j < board[0].size() - 1 && lastState[i][j + 1] == 1) count++;

                if (count < 2 || count > 3) board[i][j] = 0;
                if (count == 3 && lastState[i][j] == 0) board[i][j] = 1;
            }
        }
        return;
    }
};