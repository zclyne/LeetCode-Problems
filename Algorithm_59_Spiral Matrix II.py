class Solution:
    def generateMatrix(self, n):
        if n==0: return []
        if n==1: return[[1]]
        matrix=[[-1 for i in range(n)]for j in range(n)]
        count,cur=1,0
        while cur<n//2:
            for j in range(cur,n-cur):
                matrix[cur][j]=count
                count+=1
            for i in range(cur+1,n-cur):
                matrix[i][n-cur-1]=count
                count+=1
            for j in range(n-cur-2,cur-1,-1):
                matrix[n-cur-1][j]=count
                count+=1
            for i in range(n-cur-2,cur,-1):
                matrix[i][cur]=count
                count+=1
            cur+=1
        if n%2==1: matrix[n//2][n//2]=count
        return matrix