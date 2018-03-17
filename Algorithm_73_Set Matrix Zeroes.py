class Solution:
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        m,n=len(matrix),len(matrix[0])
        set_row=set()
        set_col=set()
        for i in range(m):
            for j in range(n):
                if matrix[i][j]==0:
                    set_row.add(i)
                    set_col.add(j)
        for i in set_row:
            for j in range(n):
                matrix[i][j]=0
        for j in set_col:
            for i in range(m):
                matrix[i][j]=0
        