class Solution:
    def getPermutation(self, nums, cur, res):
        if len(nums)==0:
            res.append(cur[:])
            return
        for i in range(len(nums)):
            cur.append(nums[i])
            nextNum=nums[:i]+nums[i+1:]
            Solution.getPermutation(self,nextNum,cur,res)
            cur.pop()
    def permute(self, nums):
        res=[]
        Solution.getPermutation(self,nums,[],res)
        return res