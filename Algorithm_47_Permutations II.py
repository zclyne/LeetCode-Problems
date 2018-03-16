class Solution:
    def getPermutation(self,nums,cur,res):
        if len(nums)==0:
            res.append(cur[:])
            return
        for i in range(len(nums)):
            if i>0 and nums[i]==nums[i-1]:
                continue
            nextNum=[]
            cur.append(nums[i])
            nextNum=nums[:i]+nums[i+1:]
            Solution.getPermutation(self,nextNum,cur,res)
            cur.pop()
    def permuteUnique(self, nums):
        nums.sort()
        res=[]
        Solution.getPermutation(self,nums,[],res)
        return res
        