class Solution:
    def BinarySearch(self, array, target, start, end):
        if start>end: return False
        if start==end:
            return array[start]==target
        mid=(start+end)//2
        if array[mid]==target:
            return True
        elif array[mid]>target:
            return Solution.BinarySearch(self,array,target,start,mid-1)
        else:
            return Solution.BinarySearch(self,array,target,mid+1,end)
    def searchMatrix(self, matrix, target):
        m=len(matrix)
        if m==0: return False
        n=len(matrix[0])
        if n==0: return False
        for i in range(m):
            if matrix[i][0]<=target and matrix[i][n-1]>=target:
                if matrix[i][0]==target or matrix[i][n-1]==target:
                    return True
                return Solution.BinarySearch(self,matrix[i],target,0,n-1)
            if matrix[i][0]>target:
                return False
        return False