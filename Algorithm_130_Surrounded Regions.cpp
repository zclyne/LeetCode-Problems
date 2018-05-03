#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    void solve(vector<vector<char>>& board)
    {
        int m=board.size();
        if (m==0) return;
        int n=board[0].size();
        vector<vector<int> > surrounded(m, vector<int>(n));
        vector<vector<int> > visited(m, vector<int>(n));
        for (int i=0;i<m;i++) for (int j=0;j<n;j++)
        {
            surrounded[i][j]=1;
            visited[i][j]=0;
        }
        for (int i=0;i<m;i++)
        {
            if (board[i][0]=='O' && !visited[i][0]) dfs(board,surrounded,visited,i,0);
            if (board[i][n-1]=='O' && !visited[i][n-1]) dfs(board,surrounded,visited,i,n-1);
        }
        for (int j=0;j<n;j++)
        {
            if (board[0][j]=='O' && !visited[0][j]) dfs(board,surrounded,visited,0,j);
            if (board[m-1][j]=='O' && !visited[m-1][j]) dfs(board,surrounded,visited,m-1,j);
        }
        for (int i=0;i<m;i++) for (int j=0;j<n;j++) if (board[i][j]=='O' && surrounded[i][j]) board[i][j]='X';
        return;
    }
    void dfs(vector<vector<char> > &board, vector<vector<int> > &surrounded, vector<vector<int> > &visited, int i, int j)
    {
        int m=board.size(), n=board[0].size();
        if (i<0 || i==m || j<0 || j==n || visited[i][j] || board[i][j]=='X') return;
        visited[i][j]=1;
        surrounded[i][j]=0;
        dfs(board,surrounded,visited,i-1,j);
        dfs(board,surrounded,visited,i+1,j);
        dfs(board,surrounded,visited,i,j-1);
        dfs(board,surrounded,visited,i,j+1);
    }
};