class Solution:
    def spiralOrder(self, matrix):
        res,row,col,m=[],0,0,len(matrix)
        if m==0: return res
        n=len(matrix[0])
        while row<m//2 and col<n//2:
            for j in range(col,n-col):
                res.append(matrix[row][j])
            for i in range(row+1,m-row):
                res.append(matrix[i][n-col-1])
            for j in range(n-col-2,col-1,-1):
                res.append(matrix[m-row-1][j])
            for i in range(m-row-2,row,-1):
                res.append(matrix[i][col])
            if row+1>=m-row-1 or col+1>=n-col-1:
                break
            row,col=row+1,col+1
        if row==m//2:
            for j in range(col,n-col):
                res.append(matrix[row][j])
        elif col==n//2:
            for i in range(row,m-row):
                res.append(matrix[i][col])
        return res

matrix=[[1,2,3],[4,5,6],[7,8,9]]
sol=Solution()
print (sol.spiralOrder(matrix))