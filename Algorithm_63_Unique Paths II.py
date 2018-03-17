class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid):
        m,n=len(obstacleGrid),len(obstacleGrid[0])
        mat=[[1 for j in range(n)] for i in range(m)]
        for i in range(m):
            if obstacleGrid[i][0]==1:
                for j in range(i,m): mat[j][0]=0
                break
        for j in range(n):
            if obstacleGrid[0][j]==1:
                for i in range(j,n): mat[0][i]=0
                break
        for i in range(1,m):
            for j in range(1,n):
                if obstacleGrid[i][j]==1:
                    mat[i][j]=0
                elif obstacleGrid[i-1][j]==0 and obstacleGrid[i][j-1]==0:
                    mat[i][j]=mat[i-1][j]+mat[i][j-1]
                elif obstacleGrid[i-1][j]==1:
                    mat[i][j]=mat[i][j-1]
                else:
                    mat[i][j]=mat[i-1][j]
        return mat[m-1][n-1]