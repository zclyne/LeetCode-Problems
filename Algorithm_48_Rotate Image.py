class Solution:
    def rotate(self, matrix):
        n=len(matrix)
        for row in range(n//2):
            for i in range(row,n-row-1):
                matrix[row][i],matrix[i][n-row-1],matrix[n-row-1][n-i-1],matrix[n-i-1][row]=matrix[n-i-1][row],matrix[row][i],matrix[i][n-row-1],matrix[n-row-1][n-i-1]