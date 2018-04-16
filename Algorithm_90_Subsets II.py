class Solution:
    def getCombinations(self, n, tmpRes, coms):
        if n==0:
            coms.append(tmpRes)
            return
        Solution.getCombinations(self, n-1, tmpRes+[0], coms)
        Solution.getCombinations(self, n-1, tmpRes+[1], coms)
    def subsetsWithDup(self, nums):
        n=len(nums)
        coms,res=[],[]
        if n==0: return []
        Solution.getCombinations(self, n, [], coms)
        for com in coms:
            tmpRes=[]
            for i in range(len(com)):
                if com[i]==1: tmpRes.append(nums[i])
            tmpRes.sort()
            if tmpRes not in res: res.append(tmpRes)
        return res
