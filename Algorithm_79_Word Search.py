class Solution:
    def dfs(self, board, word, i, j):
        if len(word)==0:
            return True
        if i<0 or i>=len(board) or j<0 or j>=len(board[0]) or word[0]!=board[i][j]:
            return False
        tmp=board[i][j]
        board[i][j]='#'
        res=self.dfs(board,word[1:],i-1,j) or self.dfs(board,word[1:],i+1,j) or self.dfs(board,word[1:],i,j-1) or self.dfs(board,word[1:],i,j+1)
        board[i][j]=tmp
        return res
    def exist(self, board, word):
        m=len(board)
        if m==0:
            return word==''
        n=len(board[0])
        if len(word)>m*n: return False
        for i in range(m):
            for j in range(n):
                if board[i][j]==word[0] and Solution.dfs(self,board,word,i,j):
                    return True
        return False